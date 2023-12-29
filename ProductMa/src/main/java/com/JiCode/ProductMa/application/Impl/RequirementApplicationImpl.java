package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.RequirementApplication;
import com.JiCode.ProductMa.application.dto.AddRequirementReqDto;
import com.JiCode.ProductMa.application.dto.AddVersionReqDto;
import com.JiCode.ProductMa.application.dto.PagedResultDto;
import com.JiCode.ProductMa.application.dto.RequirementArrResDto;
import com.JiCode.ProductMa.application.dto.RequirementDetailResDto;
import com.JiCode.ProductMa.application.dto.UpdateRequirementReqDto;
import com.JiCode.ProductMa.application.dto.UpdateVersionReqDto;
import com.JiCode.ProductMa.domain.repository.VersionRepository;
import com.JiCode.ProductMa.exception.CopyFailedException;
import com.JiCode.ProductMa.exception.CreateFailedException;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.NotFoundException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.ServerException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import com.JiCode.ProductMa.exception.requirement.SwitchVersionException;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.BacklogItemsEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.ClientsEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequirementApplicationImpl
        implements RequirementApplication {

    private static final Logger log = LoggerFactory.getLogger(RequirementApplicationImpl.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Transactional(readOnly = true)
    @Override
    public RequirementArrResDto getAllRequirementsByProductId(String productId, int pageNo, int pageSize)
            throws ServerException {
        try {
            // 根据产品id获取需求实体（分页查询了）
            PagedResultDto pagedResultDto = requirementRepository.selectRequirementsByPage(productId, pageNo, pageSize);
            RequirementEntity[] requirementEntities = pagedResultDto.getRequirements();
            // 根据产品版本查询所有内容实体，条件查询实现批量查询
            List<String> contentIds = Arrays.stream(requirementEntities)
                    .map(RequirementEntity::getRequirementContentId)
                    .collect(Collectors.toList());
            RequirementContentEntity[] requirementContentEntities = requirementRepository
                    .selectAllRequirementContentsByIds(contentIds);

            // 根据需求实体里的负责人id获取负责人名字
            String[] supervisorNames = new String[requirementContentEntities.length];
            // TODO 这边就是调用账号管理的接口了，用那个黑马的那个有一个同步调用的东西的restclient？

            // 包装在一起
            RequirementArrResDto allrequirementsDto = new RequirementArrResDto();
            RequirementArrResDto.Record[] records = new RequirementArrResDto.Record[requirementEntities.length];
            for (int i = 0; i < requirementEntities.length; i++) {
                RequirementArrResDto.Record record = new RequirementArrResDto.Record();
                record.setRequirementId(requirementEntities[i].getRequirementId());
                record.setName(requirementContentEntities[i].getName());
                record.setTypeEnum(requirementContentEntities[i].getTypeEnum());
                record.setSupervisorName(supervisorNames[i]);
                records[i] = record;
            }

            // 设置 AllrequirementsDto 的属性
            allrequirementsDto.setRecords(records);
            allrequirementsDto.setSize(pageSize);
            allrequirementsDto.setRecordNum(records.length);
            allrequirementsDto.setPages((pagedResultDto.getTotalCount() + pageSize - 1) / pageSize);
            allrequirementsDto.setCurrent(pageNo);
            allrequirementsDto.setTotal(pagedResultDto.getTotalCount());

            return allrequirementsDto;
        } catch (SelectFailedException e) {
            // 记录错误日志捏
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    @Override
    public Map<String, String> createRequirement(AddRequirementReqDto addRequirementReqDto) throws ServerException {
        try {
            // 根据参数新建一个requirementContentEntity
            RequirementContentEntity requirementContentEntity = RequirementContentEntity
                    .create(addRequirementReqDto);
            // 根据参数新建一个requirement聚合
            RequirementAggregation requirementAggregation = RequirementAggregation
                    .createNew(addRequirementReqDto.getBelongProductId(), requirementContentEntity);
            // 将这个聚合存入数据库
            String requirementId = requirementRepository.insert(requirementAggregation);
            // 返回id给前端
            return Map.of("requirementId", requirementId);
        } catch (CreateFailedException | InsertFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    @Override
    public void deleteRequirement(String requirementId) throws ServerException {
        try {
            // 根据 requirementId 删除
            requirementRepository.delete(requirementId);
        } catch (DeleteFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public RequirementDetailResDto getRequirementDetail(String requirementId) throws ServerException {
        try {
            RequirementDetailResDto requirementDetailResDto = new RequirementDetailResDto();
            RequirementAggregation requirementAggregation = requirementRepository.selectById(requirementId);
            // 写入需求基础属性
            RequirementContentEntity requirementContentEntity = requirementAggregation.getRequirementContentEntity();
            requirementContentEntity.copyPropertiesTo(requirementDetailResDto);
            // 这边写入版本属性
            VersionAggregation[] VersionAggs = requirementAggregation.getVersionsEntity().getVersionArr();
            RequirementDetailResDto.Version[] versions = new RequirementDetailResDto.Version[requirementAggregation
                    .getVersionsEntity().getVersionArr().length];
            for (int i = 0; i < versions.length; i++) {
                versions[i] = new RequirementDetailResDto.Version();
                VersionAggs[i].copyPropertiesTo(versions[i]);
            }
            // TODO 这边包一下然后写一下查外部接口的逻辑
            String supervisorId = requirementContentEntity.getSupervisorId();
            RequirementDetailResDto.Supervisor supervisor = new RequirementDetailResDto.Supervisor();
            String[] backlogItemIDArr = requirementAggregation.getBacklogItemsEntity().getBacklogItemIDArr();
            RequirementDetailResDto.BacklogItem[] backlogItems = new RequirementDetailResDto.BacklogItem[backlogItemIDArr.length];
            String[] clientIDArr = requirementAggregation.getClientsEntity().getClientIDArr();
            RequirementDetailResDto.Client[] clients = new RequirementDetailResDto.Client[clientIDArr.length];
            String[] clientNameArr = clientRepository.selectNamesById(clientIDArr);
            // 组合成 clientArr
            for (int i = 0; i < clientIDArr.length; i++) {
                clients[i] = new RequirementDetailResDto.Client();
                clients[i].setClientId(clientIDArr[i]);
                clients[i].setName(clientNameArr[i]);
            }

            requirementDetailResDto.setSupervisor(null);
            requirementDetailResDto.setVersionArr(versions);
            requirementDetailResDto.setBacklogItemArr(null);
            requirementDetailResDto.setClientArr(clients);
            return requirementDetailResDto;
        } catch (SelectFailedException | CopyFailedException | NotFoundException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    public void switchVersion(String versionId, String requirementId) throws ServerException {
        try {
            RequirementAggregation requirementAggregation = requirementRepository.selectByIdWithOnlyRV(requirementId);
            requirementAggregation.switchVersion(versionId);
        } catch (SelectFailedException | SwitchVersionException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    @Override
    public void updateRequirement(UpdateRequirementReqDto updateRequirementReqDto) throws ServerException {
        try {
            // 先查出来
            RequirementAggregation requirementAggregation = requirementRepository
                    .selectById(updateRequirementReqDto.getRequirementId());
            // 再更新聚合
            ClientsEntity clientsEntity = ClientsEntity.createByAll(updateRequirementReqDto.getClientArr());
            BacklogItemsEntity backlogItemsEntity = BacklogItemsEntity
                    .createByAll(updateRequirementReqDto.getBacklogItemArr());
            RequirementContentEntity requirementContentEntity = RequirementContentEntity
                    .create(updateRequirementReqDto);
            requirementAggregation.update(requirementContentEntity, clientsEntity, backlogItemsEntity);
            // 最后塞回去
            requirementRepository.update(requirementAggregation);
        } catch (SelectFailedException | CreateFailedException | UpdateFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    @Override
    public Map<String, String> addVersion(AddVersionReqDto addVersionReqDto) throws ServerException {
        try {
            // 先初始化一个聚合
            VersionAggregation versionAggregation = VersionAggregation.create(addVersionReqDto);
            // 最后塞回去
            String id = versionRepository.insert(addVersionReqDto.getRequirementId(), versionAggregation);
            return Map.of("id", id);
        } catch (InsertFailedException | CreateFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    @Transactional
    @Override
    public void updateVersion(UpdateVersionReqDto updateVersionReqDto) throws ServerException {
        try {
            // 先查出来
            VersionAggregation versionAggregation = versionRepository
                    .selectById(updateVersionReqDto.getId());
            // 再更新聚合
            versionAggregation.update(updateVersionReqDto);
            // 最后塞回去
            versionRepository.update(updateVersionReqDto.getRequirementId(), versionAggregation);
        } catch (SelectFailedException | UpdateFailedException | CopyFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }
}

package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.RequirementApplication;
import com.JiCode.ProductMa.application.dto.AllrequirementsDto;
import com.JiCode.ProductMa.domain.repository.VersionRepository;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.ServerException;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;

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
    VersionRepository versionRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Autowired

    @Transactional(readOnly = true)
    @Override
    public AllrequirementsDto getAllRequirementsByProductId(String productId, int pageNo, int pageSize)
            throws ServerException {
        try {
            // 根据产品id获取需求实体（分页查询了）
            RequirementEntity[] requirementEntities = requirementRepository.selectRequirementsByPage(productId, pageNo,
                    pageSize);
            // 根据产品版本查询所有内容实体 TODO 这边要手写mapper实现批量查询，用循环效率低
            RequirementContentEntity[] requirementContentEntities = new RequirementContentEntity[requirementEntities.length];
            for (int i = 0; i < requirementEntities.length; i++) {
                requirementContentEntities[i] = requirementRepository
                        .selectRequirementContent(requirementEntities[i].getRequirementContentId());
            }
            // 根据需求实体里的负责人id获取负责人名字
            String[] supervisorNames = new String[requirementContentEntities.length];
            // TODO 这边就是调用账号管理的接口了，用那个黑马的那个有一个同步调用的东西的restclient？

            // 包装在一起
            AllrequirementsDto allrequirementsDto = new AllrequirementsDto();
            AllrequirementsDto.Record[] records = new AllrequirementsDto.Record[requirementEntities.length];
            for (int i = 0; i < requirementEntities.length; i++) {
                AllrequirementsDto.Record record = new AllrequirementsDto.Record();
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
            allrequirementsDto.setPages((records.length + pageSize - 1) / pageSize);
            allrequirementsDto.setCurrent(pageNo);
            allrequirementsDto.setTotal(records.length);

            return allrequirementsDto;
        } catch (SelectFailedException e) {
            // 记录错误日志捏
            log.error("Server Error", e);
            throw new ServerException();
        }
    }
}

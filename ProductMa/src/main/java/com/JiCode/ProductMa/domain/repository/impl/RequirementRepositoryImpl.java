package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Requirement;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementContent;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementContentExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersionExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementBacklogitemMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementClientMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementContentMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementMapper;
import com.JiCode.ProductMa.application.dto.PagedResultDto;
import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
import com.JiCode.ProductMa.exception.CreateFailedException;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.VersionsEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.ClientsEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.BacklogItemsEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;

@Repository
public class RequirementRepositoryImpl implements RequirementRepository {
    @Autowired
    RequirementMapper requirementMapper;
    @Autowired
    RequirementContentMapper requirementContentMapper;
    @Autowired
    RequirementClientMapper requirementClientMapper;
    @Autowired
    RequirementBacklogitemMapper requirementBacklogitemMapper;
    @Autowired
    com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementVersionMapper requirementVersionMapper;

    /**
     * @throws SelectFailedException
     * @Description 根据 RequirementId 查询 RequirementAgg
     */
    @Override
    public RequirementAggregation selectById(String requirementId) throws SelectFailedException {
        try {
            RequirementEntity requirementEntity = selectRequirement(requirementId);
            VersionsEntity versionsEntity = selectVersions(requirementEntity.getRequirementId());
            RequirementContentEntity requirementContentEntity = selectRequirementContent(
                    requirementEntity.getRequirementContentId());
            ClientsEntity clientsEntity = selectClientIds(requirementEntity.getRequirementContentId());
            BacklogItemsEntity backlogItemsEntity = selectBacklogItemIds(requirementEntity.getRequirementContentId());

            return RequirementAggregation.createRequirementByAll(
                    requirementEntity, versionsEntity, requirementContentEntity, clientsEntity, backlogItemsEntity);
        } catch (CreateFailedException e) {
            throw new SelectFailedException("Failed to select due to creation failure: " + e.getMessage(), e);
        }
    }

    /**
     * @Description 根据 RequirementId 查询 RequirementAgg，只包含 RequirementEntity 和
     *              VersionsEntity
     */
    public RequirementAggregation selectByIdWithOnlyRV(String requirementId) throws SelectFailedException {
        try {
            RequirementEntity requirementEntity = selectRequirement(requirementId);
            VersionsEntity versionsEntity = selectVersions(requirementEntity.getRequirementId());
            return RequirementAggregation.createRequirementByOnlyRV(requirementEntity, versionsEntity);
        } catch (CreateFailedException e) {
            throw new SelectFailedException("Failed to select due to creation failure: " + e.getMessage(), e);
        }
    }

    /**
     * @Description 根据 versionContentIds 批量查询 RequirementContentEntity
     */
    @Override
    public RequirementContentEntity[] selectAllRequirementContentsByIds(List<String> contentIds)
            throws SelectFailedException {
        RequirementContentExample example = new RequirementContentExample();
        example.createCriteria().andVersionContentIdIn(contentIds);

        List<RequirementContent> requirementContents = requirementContentMapper
                .selectByExample(example);

        // 创建一个新的列表，用于存放排序后的结果
        List<RequirementContent> sortedRequirementContents = new ArrayList<>();

        // 遍历 contentIds，按照 contentIds 的顺序添加结果到 sortedRequirementContents
        for (String contentId : contentIds) {
            for (RequirementContent requirementContent : requirementContents) {
                if (requirementContent.getVersionContentId().equals(contentId)) {
                    sortedRequirementContents.add(requirementContent);
                    break;
                }
            }
        }

        RequirementContentEntity[] requirementContentEntities = new RequirementContentEntity[sortedRequirementContents
                .size()];

        try {
            int i = 0;
            for (RequirementContent requirementContent : sortedRequirementContents) {
                requirementContentEntities[i] = RequirementContentEntity.create(requirementContent);
                i++;
            }
            return requirementContentEntities;
        } catch (CreateFailedException e) {
            throw new SelectFailedException("Failed to create RequirementContentEntity for requirementContent: "
                    + e.getMessage(), e);
        }
    }

    /**
     * @Description 根据 productId 查询所有 RequirementEntity，分页版
     */
    @Override
    public PagedResultDto selectRequirementsByPage(String productId, int pageNo, int pageSize)
            throws SelectFailedException {
        RequirementExample example = new RequirementExample();
        example.createCriteria().andBelongProductIdEqualTo(productId);
        RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
        List<Requirement> requirements = requirementMapper.selectByExampleWithRowbounds(example, rowBounds);
        // 这边因为他自己的方法不会返回总页数等信息，必须自己再查一遍，看看能不能优化吧 TODO
        long totalCount = requirementMapper.countByExample(example);
        RequirementEntity[] requirementEntities = new RequirementEntity[requirements.size()];
        try {
            int i = 0;
            for (Requirement requirement : requirements) {
                RequirementEntity requirementEntity = RequirementEntity.create(requirement);
                requirementEntities[i] = requirementEntity;
                i++;
            }
        } catch (CreateFailedException e) {
            throw new SelectFailedException("Failed to create RequirementEntity for requirement: " + e.getMessage(), e);
        }
        PagedResultDto result = new PagedResultDto();
        result.setRequirements(requirementEntities);
        result.setTotalCount((int) totalCount);
        return result;
    }

    @Override
    public RequirementEntity selectRequirement(String requirementId) throws SelectFailedException {
        Requirement requirement = this.requirementMapper.selectByPrimaryKey(requirementId);
        if (requirement == null) {
            throw new SelectFailedException("Requirement with id " + requirementId + " not found.");
        }
        try {
            return RequirementEntity.create(requirement);
        } catch (CreateFailedException e) {
            throw new SelectFailedException(
                    "Failed to create RequirementEntity for requirement with id " + requirementId, e);
        }
    }

    private ClientsEntity selectClientIds(String requirementContentId)
            throws SelectFailedException {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(requirementContentId);
        List<RequirementClientKey> keys = this.requirementClientMapper.selectByExample(example);
        try {
            return ClientsEntity
                    .createByAll(keys.stream().map(RequirementClientKey::getClientId).toArray(String[]::new));
        } catch (CreateFailedException e) {
            throw new SelectFailedException(
                    "Failed to create ClientsEntity for versionContentId " + requirementContentId + ".", e);
        }
    }

    private BacklogItemsEntity selectBacklogItemIds(String requirementContentId) throws SelectFailedException {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(requirementContentId);
        List<RequirementBacklogitemKey> keys = this.requirementBacklogitemMapper.selectByExample(example);
        try {
            return BacklogItemsEntity
                    .createByAll(keys.stream().map(RequirementBacklogitemKey::getBacklogitemId).toArray(String[]::new));
        } catch (CreateFailedException e) {
            throw new SelectFailedException(
                    "Failed to create BacklogItemsEntity for versionContentId " + requirementContentId + ".", e);
        }
    }

    @Override
    public VersionsEntity selectVersions(String requirementId) throws SelectFailedException {
        RequirementVersionExample example = new RequirementVersionExample();
        RequirementVersionExample.Criteria criteria = example.createCriteria();
        criteria.andBelongRequirementIdEqualTo(requirementId);
        List<RequirementVersion> versions = this.requirementVersionMapper.selectByExample(example);
        if (versions == null || versions.isEmpty()) {
            throw new SelectFailedException("Versions not found for requirement with id " + requirementId + ".");
        }
        List<VersionAggregation> versionAggs = new ArrayList<>();
        for (RequirementVersion version : versions) {
            try {
                versionAggs.add(VersionAggregation.createVersionByAll(version));
            } catch (CreateFailedException e) {
                throw new SelectFailedException(
                        "Failed to create VersionAggregation for requirement with id " + requirementId + ".", e);
            }
        }
        try {
            return VersionsEntity.createByAll(versionAggs.toArray(new VersionAggregation[0]));
        } catch (CreateFailedException e) {
            throw new SelectFailedException(
                    "Failed to create VersionsEntity for requirement with id " + requirementId + ".", e);
        }
    }

    /**
     * @Description 根据 versionId 查询 requirementContent
     */
    @Override
    public RequirementContentEntity selectRequirementContent(String versionId) throws SelectFailedException {
        RequirementContent content = this.requirementContentMapper.selectByPrimaryKey(versionId);
        if (content == null) {
            throw new SelectFailedException("RequirementContent not found for version id " + versionId + ".");
        }
        try {
            return RequirementContentEntity.create(content);
        } catch (CreateFailedException e) {
            throw new SelectFailedException(
                    "Failed to create RequirementContentEntity for version id " + versionId + ".", e);
        }
    }

    /**
     * @Description 插入新的 RequirementAgg
     */
    @Override
    public String insert(RequirementAggregation requirementAggregation)
            throws InsertFailedException {
        // 生成UUID
        String requirementContentId = UUID.randomUUID().toString();
        String requirementId = UUID.randomUUID().toString();
        requirementAggregation.getRequirementEntity().setRequirementContentId(requirementContentId);
        requirementAggregation.getRequirementEntity().setId(requirementId);
        requirementAggregation.getVersionsEntity().getVersionArr()[0].setId(requirementContentId);
        // 新建的时候只有一个版本
        Requirement requirement = insertRequirement(requirementAggregation);
        insertVersion(requirementAggregation, requirement.getRequirementId());
        insertRequirementContent(requirementAggregation);
        insertClients(requirementAggregation);
        insertBacklogItems(requirementAggregation);
        return requirementId;
    }

    private void insertRequirementContent(RequirementAggregation requirementAggregation) throws InsertFailedException {
        RequirementContent requirementContent = new RequirementContent();
        BeanUtils.copyProperties(requirementAggregation.getRequirementContentEntity(), requirementContent);
        requirementContent.setVersionContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
        int requirementContentResult = this.requirementContentMapper.insert(requirementContent);
        if (requirementContentResult <= 0) {
            throw new InsertFailedException("Failed to insert requirement content with versionContentId: "
                    + requirementContent.getVersionContentId());
        }
    }

    private Requirement insertRequirement(RequirementAggregation requirementAggregation)
            throws InsertFailedException {
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(requirementAggregation.getRequirementEntity(), requirement);
        int requirementResult = this.requirementMapper.insert(requirement);
        if (requirementResult <= 0) {
            throw new InsertFailedException("Failed to insert requirement for RequirementAggregation with id "
                    + requirementAggregation.getRequirementEntity().getRequirementId() + ".");
        }
        return requirement;
    }

    private void insertClients(RequirementAggregation requirementAggregation)
            throws InsertFailedException {
        for (String clientId : requirementAggregation.getClientsEntity().getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey
                    .setRequirementContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new InsertFailedException(
                        "Failed to insert client with id " + clientId + " for RequirementAggregation with inVersionId "
                                + requirementAggregation.getRequirementEntity().getRequirementContentId() + ".");
            }
        }
    }

    private void insertBacklogItems(RequirementAggregation requirementAggregation)
            throws InsertFailedException {
        for (String backlogItemId : requirementAggregation.getBacklogItemsEntity().getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey
                    .setRequirementContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new InsertFailedException("Failed to insert backlog item with id " + backlogItemId
                        + " for RequirementAggregation with inVersionId "
                        + requirementAggregation.getRequirementEntity().getRequirementContentId() + ".");
            }
        }
    }

    private void insertVersion(RequirementAggregation requirementAggregation, String requirementId)
            throws InsertFailedException {
        VersionAggregation versionAgg = requirementAggregation.getVersionsEntity().getVersionArr()[0];
        RequirementVersion requirementVersion = new RequirementVersion();
        BeanUtils.copyProperties(versionAgg, requirementVersion);
        requirementVersion.setBelongRequirementId(requirementId);
        int versionResult = this.requirementVersionMapper.insert(requirementVersion);
        if (versionResult <= 0) {
            throw new InsertFailedException("Failed to insert version for RequirementAggregation with id "
                    + requirementAggregation.getRequirementEntity().getRequirementId() + " and requirement id "
                    + requirementId
                    + ".");
        }
    }

    /**
     * @Description 删除 RequirementAgg
     */
    @Override
    public void delete(String requirementID) throws DeleteFailedException {
        // 因为数据库里设置了级联删除，所以这里的实现比较容易
        int result = requirementMapper.deleteByPrimaryKey(requirementID);
        if (result <= 0) {
            throw new DeleteFailedException("Failed to delete requirement with id " + requirementID + ".");
        }
    }

    /**
     * @Description 更新 RequirementAgg，注意只更新当前 versionContent，如需更新其他版本要切过去，否则只能更新
     *              version 的基础内容
     */
    @Override
    public void update(RequirementAggregation requirementAggregation)
            throws UpdateFailedException {
        // 检查 requirementAggregation 是否为 null
        if (requirementAggregation == null) {
            throw new IllegalArgumentException("requirementAggregation cannot be null");
        }

        // 对脏标记进行处理，优化 update 语句
        if (requirementAggregation.getRequirementEntity() != null && requirementAggregation.isRequirementDirty()) {
            updateRequirement(requirementAggregation.getRequirementEntity());
        }
        if (requirementAggregation.getVersionsEntity() != null && requirementAggregation.isVersionsDirty()) {
            updateVersions(requirementAggregation);
        }
        if (requirementAggregation.getRequirementContentEntity() != null
                && requirementAggregation.isRequirementContentDirty()) {
            updateRequirementContent(requirementAggregation);
        }
        if (requirementAggregation.getClientsEntity() != null && requirementAggregation.isClientsDirty()) {
            updateClients(requirementAggregation);
        }
        if (requirementAggregation.getBacklogItemsEntity() != null && requirementAggregation.isBacklogItemsDirty()) {
            updateBacklogItems(requirementAggregation);
        }
        requirementAggregation.cleanDirty();
    }

    private void updateRequirementContent(RequirementAggregation requirementAggregation)
            throws UpdateFailedException {
        RequirementContent requirementContent = new RequirementContent();
        BeanUtils.copyProperties(requirementAggregation.getRequirementContentEntity(), requirementContent);
        requirementContent.setVersionContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
        int requirementContentResult = this.requirementContentMapper.updateByPrimaryKey(requirementContent);
        if (requirementContentResult <= 0) {
            throw new UpdateFailedException("Failed to update RequirementContent for RequirementAggregation");
        }
    }

    private void updateRequirement(RequirementEntity requirementEntity)
            throws UpdateFailedException {
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(requirementEntity, requirement);
        int requirementResult = this.requirementMapper.updateByPrimaryKey(requirement);
        if (requirementResult <= 0) {
            throw new UpdateFailedException("Failed to update requirement for RequirementAggregation with id "
                    + requirementEntity.getRequirementId() + ".");
        }
    }

    private void updateClients(RequirementAggregation requirementAggregation) throws UpdateFailedException {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(
                requirementAggregation.getRequirementEntity().getRequirementContentId());
        this.requirementClientMapper.deleteByExample(example);

        for (String clientId : requirementAggregation.getClientsEntity().getClientIDArr()) {
            RequirementClientKey key = new RequirementClientKey();
            key.setRequirementContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
            key.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(key);
            if (clientResult <= 0) {
                throw new UpdateFailedException(
                        "Failed to update client with id " + clientId + " for RequirementAggregation with inVersion "
                                + requirementAggregation.getRequirementEntity().getRequirementContentId() + ".");
            }
        }
    }

    private void updateBacklogItems(RequirementAggregation requirementAggregation)
            throws UpdateFailedException {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(
                requirementAggregation.getRequirementEntity().getRequirementContentId());
        this.requirementBacklogitemMapper.deleteByExample(example);

        for (String backlogItemId : requirementAggregation.getBacklogItemsEntity().getBacklogItemIDArr()) {
            RequirementBacklogitemKey key = new RequirementBacklogitemKey();
            key.setRequirementContentId(requirementAggregation.getRequirementEntity().getRequirementContentId());
            key.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(key);
            if (backlogItemResult <= 0) {
                throw new UpdateFailedException("Failed to update backlog item with id " + backlogItemId
                        + " for RequirementAggregation with inVersion "
                        + requirementAggregation.getRequirementEntity().getRequirementContentId() + ".");
            }
        }
    }

    private void updateVersions(RequirementAggregation requirementAggregation) throws UpdateFailedException {
        for (VersionAggregation version : requirementAggregation.getVersionsEntity().getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            requirementVersion.setId(version.getId());
            requirementVersion.setBelongRequirementId(requirementAggregation.getRequirementEntity().getRequirementId());
            int versionResult = this.requirementVersionMapper.updateByPrimaryKey(requirementVersion);
            if (versionResult <= 0) {
                throw new UpdateFailedException(
                        "Failed to update version with id " + version.getId()
                                + " for RequirementAggregation with id "
                                + requirementAggregation.getRequirementEntity().getRequirementId() + ".");
            }
        }
    }
}

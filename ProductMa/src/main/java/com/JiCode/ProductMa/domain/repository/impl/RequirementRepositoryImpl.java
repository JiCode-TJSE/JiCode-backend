package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Requirement;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementContent;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersionExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementBacklogitemMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementClientMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementContentMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementMapper;
import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.VersionContentEntity;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
import com.JiCode.ProductMa.exception.requirement.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
     * @Description 根据 RequirementId 查询 RequirementAgg
     */
    @Override
    public RequirementAggregation selectById(String id)
            throws RequirementNotFoundException, ClientNotFoundException, BacklogItemNotFoundException,
            VersionNotFoundException {
        Requirement requirement = selectRequirement(id);
        VersionAggregation[] versionArr = selectVersions(requirement.getId());
        RequirementContent requirementContent = selectRequirementContent(requirement.getInVersion(), versionArr);
        String[] clientIdArr = selectClientIds(requirementContent.getVersionContentId());
        String[] backlogItemIdArr = selectBacklogItemIds(requirementContent.getVersionContentId());

        return RequirementAggregation.createRequirementByAll(
                requirement.getId(),
                requirement.getInVersion(),
                requirementContent.getName(),
                requirementContent.getDetail(),
                requirement.getBelongProductId(),
                requirementContent.getSupervisorId(),
                requirementContent.getModuleEnum(),
                requirementContent.getSourceEnum(),
                requirementContent.getTypeEnum(),
                clientIdArr,
                backlogItemIdArr,
                versionArr,
                "");
    }

    private RequirementContent selectRequirementContent(String versionId, VersionAggregation[] versionArr) {
        Optional<VersionAggregation> versionOpt = Arrays.stream(versionArr)
                .filter(version -> versionId.equals(version.getId()))
                .findFirst();
        if (!versionOpt.isPresent()) {
            // 抛出异常
        }
        VersionAggregation version = versionOpt.get();
        RequirementContent content = this.requirementContentMapper.selectByPrimaryKey(version.getId());
        if (content == null) {
            // 抛出异常
        }
        return content;
    }

    private Requirement selectRequirement(String id) throws RequirementNotFoundException {
        Requirement requirement = this.requirementMapper.selectByPrimaryKey(id);
        if (requirement == null) {
            throw new RequirementNotFoundException("Requirement not found.");
        }
        return requirement;
    }

    private String[] selectClientIds(String versionContentId) throws ClientNotFoundException {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(versionContentId);
        List<RequirementClientKey> keys = this.requirementClientMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new ClientNotFoundException("Client not found.");
        }
        return keys.stream().map(RequirementClientKey::getClientId).toArray(String[]::new);
    }

    private String[] selectBacklogItemIds(String versionContentId) throws BacklogItemNotFoundException {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(versionContentId);
        List<RequirementBacklogitemKey> keys = this.requirementBacklogitemMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new BacklogItemNotFoundException("Backlogitem not found.");
        }
        return keys.stream().map(RequirementBacklogitemKey::getBacklogitemId).toArray(String[]::new);
    }

    private VersionAggregation[] selectVersions(String requirementId) throws VersionNotFoundException {
        RequirementVersionExample example = new RequirementVersionExample();
        RequirementVersionExample.Criteria criteria = example.createCriteria();
        criteria.andBelongRequirementIdEqualTo(requirementId);
        List<RequirementVersion> versions = this.requirementVersionMapper.selectByExample(example);
        if (versions == null || versions.isEmpty()) {
            throw new VersionNotFoundException("Version not found.");
        }
        return versions.stream().map(version -> VersionAggregation.createVersionByAll(
                version.getId(),
                version.getName(),
                version.getDetail(),
                version.getCreateTime())).toArray(VersionAggregation[]::new);
    }

    /**
     * @Description 根据 versionId 查询 VersionContent
     */
    @Override
    public VersionContentEntity selectVersionContent(String versionId) {
        RequirementContent content = this.requirementContentMapper.selectByPrimaryKey(versionId);
        VersionContentEntity versionContent = new VersionContentEntity();
        BeanUtils.copyProperties(content, versionContent);
        return versionContent;
    }

    /**
     * @Description 插入新的 RequirementAgg
     */
    @Override
    public void insert(RequirementAggregation requirementAggregation)
            throws InsertRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException,
            InsertVersionFailedException {
        Requirement requirement = insertRequirement(requirementAggregation);
        // 新建的时候只有一个版本？
        insertVersion(requirementAggregation, requirement.getId());
        insertRequirementContent(requirementAggregation);
        insertClients(requirementAggregation);
        insertBacklogItems(requirementAggregation);
    }

    private void insertRequirementContent(RequirementAggregation requirementAggregation) {
        RequirementContent requirementContent = new RequirementContent();
        BeanUtils.copyProperties(requirementAggregation, requirementContent);
        // TODO 这边改下名
        requirementContent.setVersionContentId(requirementAggregation.getInVersion());
        int requirementContentResult = this.requirementContentMapper.insert(requirementContent);
        if (requirementContentResult <= 0) {
            // 抛出异常
        }
    }

    private Requirement insertRequirement(RequirementAggregation requirementAggregation)
            throws InsertRequirementFailedException {
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(requirementAggregation, requirement);
        int requirementResult = this.requirementMapper.insert(requirement);
        if (requirementResult <= 0) {
            throw new InsertRequirementFailedException("Insert requirement failed.");
        }
        return requirement;
    }

    private void insertClients(RequirementAggregation requirementAggregation)
            throws InsertClientFailedException {
        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey.setRequirementContentId(requirementAggregation.getInVersion());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new InsertClientFailedException("Insert client failed.");
            }
        }
    }

    private void insertBacklogItems(RequirementAggregation requirementAggregation)
            throws InsertBacklogItemFailedException {
        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey.setRequirementContentId(requirementAggregation.getInVersion());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new InsertBacklogItemFailedException("Insert backlogitem failed.");
            }
        }
    }

    private void insertVersion(RequirementAggregation requirementAggregation, String requirementId)
            throws InsertVersionFailedException {
        VersionAggregation version = requirementAggregation.getVersionArr()[0];
        RequirementVersion requirementVersion = new RequirementVersion();
        String versionId = version.getId();
        requirementVersion.setId(versionId);
        requirementVersion.setBelongRequirementId(requirementId);
        int versionResult = this.requirementVersionMapper.insert(requirementVersion);
        if (versionResult <= 0) {
            throw new InsertVersionFailedException("Insert version failed.");
        }
    }

    /**
     * @Description 删除 RequirementAgg
     */
    @Override
    public void delete(String requirementID) throws DeleteRequirementFailedException {
        // 因为数据库里设置了级联删除，所以这里的实现比较容易
        int result = requirementMapper.deleteByPrimaryKey(requirementID);
        if (result <= 0) {
            throw new DeleteRequirementFailedException("Delete requirement failed.");
        }
    }

    /**
     * @Description 更新 RequirementAgg，注意只更新当前 versionContent，如需更新其他版本要切过去，否则只能更新
     *              version 的基础内容
     */
    @Override
    public void update(RequirementAggregation requirementAggregation)
            throws UpdateRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException {
        // 对脏标记进行处理，优化 update 语句
        if (requirementAggregation.isRequirementDirty()) {
            updateRequirement(requirementAggregation);
        }
        if (requirementAggregation.isVersionDirty()) {
            updateVersions(requirementAggregation);
        }
        if (requirementAggregation.isContentDirty()) {
            updateRequirementContent(requirementAggregation);
        }
        if (requirementAggregation.isClientDirty()) {
            updateClients(requirementAggregation);
        }
        if (requirementAggregation.isBacklogItemDirty()) {
            updateBacklogItems(requirementAggregation);
        }
        requirementAggregation.cleanDirty();
    }

    private void updateRequirementContent(RequirementAggregation requirementAggregation) {
        RequirementContent requirementContent = new RequirementContent();
        BeanUtils.copyProperties(requirementAggregation, requirementContent);
        int requirementContentResult = this.requirementContentMapper.updateByPrimaryKey(requirementContent);
        if (requirementContentResult <= 0) {
            // 抛出异常
        }
    }

    private void updateRequirement(RequirementAggregation requirementAggregation)
            throws UpdateRequirementFailedException {
        Requirement requirement = new Requirement();
        BeanUtils.copyProperties(requirementAggregation, requirement);
        int requirementResult = this.requirementMapper.updateByPrimaryKey(requirement);
        if (requirementResult <= 0) {
            throw new UpdateRequirementFailedException("Update requirement failed.");
        }
    }

    private void updateClients(RequirementAggregation requirementAggregation) throws InsertClientFailedException {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(requirementAggregation.getInVersion());
        this.requirementClientMapper.deleteByExample(example);

        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey key = new RequirementClientKey();
            key.setRequirementContentId(requirementAggregation.getInVersion());
            key.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(key);
            if (clientResult <= 0) {
                throw new InsertClientFailedException("Insert client failed.");
            }
        }
    }

    private void updateBacklogItems(RequirementAggregation requirementAggregation)
            throws InsertBacklogItemFailedException {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementContentIdEqualTo(requirementAggregation.getInVersion());
        this.requirementBacklogitemMapper.deleteByExample(example);

        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey key = new RequirementBacklogitemKey();
            key.setRequirementContentId(requirementAggregation.getInVersion());
            key.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(key);
            if (backlogItemResult <= 0) {
                throw new InsertBacklogItemFailedException("Insert backlogitem failed.");
            }
        }
    }

    private void updateVersions(RequirementAggregation requirementAggregation) throws UpdateRequirementFailedException {
        for (VersionAggregation version : requirementAggregation.getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            requirementVersion.setId(version.getId());
            requirementVersion.setBelongRequirementId(requirementAggregation.getId());
            int versionResult = this.requirementVersionMapper.updateByPrimaryKey(requirementVersion);
            if (versionResult <= 0) {
                throw new UpdateRequirementFailedException("Update requirement failed.");
            }
        }
    }
}

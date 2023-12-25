package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Requirement;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersionExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementBacklogitemMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementClientMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementMapper;
import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
import com.JiCode.ProductMa.exception.requirement.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeanUtils;

@Repository
public class RequirementRepositoryImpl implements RequirementRepository {
    @Autowired
    RequirementMapper requirementMapper;
    @Autowired
    RequirementClientMapper requirementClientMapper;
    @Autowired
    RequirementBacklogitemMapper requirementBacklogitemMapper;
    @Autowired
    com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementVersionMapper requirementVersionMapper;

    /**
     * @Description 根据 RequirementId 查询 RequirementAggregation
     */
    public RequirementAggregation selectById(String id)
            throws RequirementNotFoundException, ClientNotFoundException, BacklogItemNotFoundException,
            VersionNotFoundException {
        Requirement requirement = selectRequirement(id);
        String[] clientIdArr = selectClientIds(requirement);
        String[] backlogItemIdArr = selectBacklogItemIds(requirement);
        VersionAggregation[] versionArr = selectVersions(requirement);

        return RequirementAggregation.createRequirementByAll(
                requirement.getId(),
                requirement.getName(),
                requirement.getDetail(),
                requirement.getBelongProductId(),
                requirement.getSupervisorId(),
                requirement.getModuleEnum(),
                requirement.getSourceEnum(),
                requirement.getTypeEnum(),
                clientIdArr,
                backlogItemIdArr,
                versionArr,
                "");
    }

    private Requirement selectRequirement(String id) throws RequirementNotFoundException {
        Requirement requirement = this.requirementMapper.selectByPrimaryKey(id);
        if (requirement == null) {
            throw new RequirementNotFoundException("Requirement not found.");
        }
        return requirement;
    }

    private String[] selectClientIds(Requirement requirement) throws ClientNotFoundException {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementIdEqualTo(requirement.getId());
        List<RequirementClientKey> keys = this.requirementClientMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new ClientNotFoundException("Client not found.");
        }
        return keys.stream().map(RequirementClientKey::getClientId).toArray(String[]::new);
    }

    private String[] selectBacklogItemIds(Requirement requirement) throws BacklogItemNotFoundException {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementIdEqualTo(requirement.getId());
        List<RequirementBacklogitemKey> keys = this.requirementBacklogitemMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new BacklogItemNotFoundException("Backlogitem not found.");
        }
        return keys.stream().map(RequirementBacklogitemKey::getBacklogitemId).toArray(String[]::new);
    }

    private VersionAggregation[] selectVersions(Requirement requirement) throws VersionNotFoundException {
        RequirementVersionExample example = new RequirementVersionExample();
        RequirementVersionExample.Criteria criteria = example.createCriteria();
        criteria.andBelongRequirementIdEqualTo(requirement.getId());
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
     * @Description 插入 RequirementAggregation
     */
    public void insert(RequirementAggregation requirementAggregation)
            throws InsertRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException,
            InsertVersionFailedException {
        Requirement requirement = insertRequirement(requirementAggregation);
        insertClients(requirementAggregation, requirement);
        insertBacklogItems(requirementAggregation, requirement);
        insertVersions(requirementAggregation, requirement);
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

    private void insertClients(RequirementAggregation requirementAggregation, Requirement requirement)
            throws InsertClientFailedException {
        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey.setRequirementId(requirement.getId());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new InsertClientFailedException("Insert client failed.");
            }
        }
    }

    private void insertBacklogItems(RequirementAggregation requirementAggregation, Requirement requirement)
            throws InsertBacklogItemFailedException {
        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey.setRequirementId(requirement.getId());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new InsertBacklogItemFailedException("Insert backlogitem failed.");
            }
        }
    }

    private void insertVersions(RequirementAggregation requirementAggregation, Requirement requirement)
            throws InsertVersionFailedException {
        for (VersionAggregation version : requirementAggregation.getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            String versionId = version.getId();
            requirementVersion.setId(versionId);
            requirementVersion.setBelongRequirementId(requirement.getId());
            int versionResult = this.requirementVersionMapper.insert(requirementVersion);
            if (versionResult <= 0) {
                throw new InsertVersionFailedException("Insert version failed.");
            }
        }
    }

    /**
     * @Description 删除 RequirementAggregation
     */
    public void delete(String requirementID) throws DeleteRequirementFailedException {
        // 因为数据库里设置了级联删除，所以这里的实现比较容易
        int result = requirementMapper.deleteByPrimaryKey(requirementID);
        if (result <= 0) {
            throw new DeleteRequirementFailedException("Delete requirement failed.");
        }
    }

    /**
     * @Description 更新 RequirementAggregation
     */
    public void update(RequirementAggregation requirementAggregation)
            throws UpdateRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException {
        // 对脏标记进行处理，优化 update 语句
        if (requirementAggregation.isRequirementDirty()) {
            updateRequirement(requirementAggregation);
        }
        if (requirementAggregation.isClientDirty()) {
            updateClients(requirementAggregation);
        }
        if (requirementAggregation.isBacklogItemDirty()) {
            updateBacklogItems(requirementAggregation);
        }
        if (requirementAggregation.isVersionDirty()) {
            updateVersions(requirementAggregation);
        }
        requirementAggregation.cleanDirty();
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
        criteria.andRequirementIdEqualTo(requirementAggregation.getId());
        this.requirementClientMapper.deleteByExample(example);

        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey key = new RequirementClientKey();
            key.setRequirementId(requirementAggregation.getId());
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
        criteria.andRequirementIdEqualTo(requirementAggregation.getId());
        this.requirementBacklogitemMapper.deleteByExample(example);

        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey key = new RequirementBacklogitemKey();
            key.setRequirementId(requirementAggregation.getId());
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

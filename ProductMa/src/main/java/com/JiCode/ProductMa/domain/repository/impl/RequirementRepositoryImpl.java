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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public RequirementAggregation selectById(String id) throws Exception {
        Requirement requirement = selectRequirement(id);
        String[] clientIdArr = selectClientIds(requirement);
        String[] backlogItemIdArr = selectBacklogItemIds(requirement);
        VersionAggregation[] versionArr = selectVersions(requirement);

        return RequirementAggregation.createRequirementByAll(
                requirement.getId(),
                requirement.getTitle(),
                requirement.getDetail(),
                requirement.getProductId(),
                requirement.getSupervisorId(),
                requirement.getModule(),
                requirement.getSource(),
                requirement.getType(),
                requirement.getValue(),
                clientIdArr,
                backlogItemIdArr,
                versionArr,
                "");
    }

    private Requirement selectRequirement(String id) throws Exception {
        Requirement requirement = this.requirementMapper.selectByPrimaryKey(id);
        if (requirement == null) {
            throw new Exception("Requirement not found.");
        }
        return requirement;
    }

    private String[] selectClientIds(Requirement requirement) throws Exception {
        RequirementClientExample example = new RequirementClientExample();
        RequirementClientExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementIdEqualTo(requirement.getId());
        List<RequirementClientKey> keys = this.requirementClientMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new Exception("Client not found.");
        }
        return keys.stream().map(RequirementClientKey::getClientId).toArray(String[]::new);
    }

    private String[] selectBacklogItemIds(Requirement requirement) throws Exception {
        RequirementBacklogitemExample example = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementIdEqualTo(requirement.getId());
        List<RequirementBacklogitemKey> keys = this.requirementBacklogitemMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()) {
            throw new Exception("Backlogitem not found.");
        }
        return keys.stream().map(RequirementBacklogitemKey::getBacklogitemId).toArray(String[]::new);
    }

    private VersionAggregation[] selectVersions(Requirement requirement) throws Exception {
        RequirementVersionExample example = new RequirementVersionExample();
        RequirementVersionExample.Criteria criteria = example.createCriteria();
        criteria.andRequirementIdEqualTo(requirement.getId());
        List<RequirementVersion> versions = this.requirementVersionMapper.selectByExample(example);
        if (versions == null || versions.isEmpty()) {
            throw new Exception("Version not found.");
        }
        return versions.stream().map(version -> VersionAggregation.createVersionByAll(
                version.getId(),
                version.getName(),
                version.getDetail(),
                version.getCreateTime())).toArray(VersionAggregation[]::new);
    }

    public void insert(RequirementAggregation requirementAggregation) throws Exception {
        Requirement requirement = insertRequirement(requirementAggregation);
        insertClients(requirementAggregation, requirement);
        insertBacklogItems(requirementAggregation, requirement);
        insertVersions(requirementAggregation, requirement);
    }

    private Requirement insertRequirement(RequirementAggregation requirementAggregation) throws Exception {
        Requirement requirement = new Requirement();
        requirement.setId(requirementAggregation.getId());
        requirement.setTitle(requirementAggregation.getTitle());
        requirement.setDetail(requirementAggregation.getDescription());
        requirement.setProductId(requirementAggregation.getBelongProductID());
        requirement.setSupervisorId(requirementAggregation.getSupervisorID());
        requirement.setModule(requirementAggregation.getModuleType());
        requirement.setSource(requirementAggregation.getSourceType());
        requirement.setType(requirementAggregation.getTypeType());
        requirement.setValue(requirementAggregation.getValueType());
        int requirementResult = this.requirementMapper.insert(requirement);
        if (requirementResult <= 0) {
            throw new Exception("Insert requirement failed.");
        }
        return requirement;
    }

    private void insertClients(RequirementAggregation requirementAggregation, Requirement requirement)
            throws Exception {
        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey.setRequirementId(requirement.getId());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new Exception("Insert client failed.");
            }
        }
    }

    private void insertBacklogItems(RequirementAggregation requirementAggregation, Requirement requirement)
            throws Exception {
        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey.setRequirementId(requirement.getId());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new Exception("Insert backlogitem failed.");
            }
        }
    }

    private void insertVersions(RequirementAggregation requirementAggregation, Requirement requirement)
            throws Exception {
        for (VersionAggregation version : requirementAggregation.getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            String versionId = version.getId();
            requirementVersion.setId(versionId);
            requirementVersion.setRequirementId(requirement.getId());
            int versionResult = this.requirementVersionMapper.insert(requirementVersion);
            if (versionResult <= 0) {
                throw new Exception("Insert version failed.");
            }
        }
    }

    public void delete(String requirementID) throws Exception {
        // 因为数据库里设置了级联删除，所以这里的实现比较容易
        int result = requirementMapper.deleteByPrimaryKey(requirementID);
        if (result <= 0) {
            throw new Exception("Delete requirement failed.");
        }
    }

    public void update(RequirementAggregation requirementAggregation) throws Exception {
        updateRequirement(requirementAggregation);
        updateClients(requirementAggregation);
        updateBacklogItems(requirementAggregation);
        updateVersions(requirementAggregation);
    }

    private void updateRequirement(RequirementAggregation requirementAggregation) throws Exception {
        Requirement requirement = new Requirement();
        requirement.setId(requirementAggregation.getId());
        requirement.setTitle(requirementAggregation.getTitle());
        requirement.setDetail(requirementAggregation.getDescription());
        requirement.setProductId(requirementAggregation.getBelongProductID());
        requirement.setSupervisorId(requirementAggregation.getSupervisorID());
        requirement.setModule(requirementAggregation.getModuleType());
        requirement.setSource(requirementAggregation.getSourceType());
        requirement.setType(requirementAggregation.getTypeType());
        requirement.setValue(requirementAggregation.getValueType());
        int requirementResult = this.requirementMapper.updateByPrimaryKey(requirement);
        if (requirementResult <= 0) {
            throw new Exception("Update requirement failed.");
        }
    }

    private void updateClients(RequirementAggregation requirementAggregation) throws Exception {
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
                throw new Exception("Insert client failed.");
            }
        }
    }

    private void updateBacklogItems(RequirementAggregation requirementAggregation) throws Exception {
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
                throw new Exception("Insert backlogitem failed.");
            }
        }
    }

    private void updateVersions(RequirementAggregation requirementAggregation) throws Exception {
        for (VersionAggregation version : requirementAggregation.getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            requirementVersion.setId(version.getId());
            requirementVersion.setRequirementId(requirementAggregation.getId());
            int versionResult = this.requirementVersionMapper.updateByPrimaryKey(requirementVersion);
            if (versionResult <= 0) {
                throw new Exception("Update version failed.");
            }
        }
    }
}

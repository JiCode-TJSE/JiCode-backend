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

import java.util.ArrayList;
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
        // requirement 实体查询
        Requirement requirement = this.requirementMapper.selectByPrimaryKey(id);
        if (requirement == null) {
            throw new Exception("Requirement not found.");
        }

        // 条件查询 -- client
        RequirementClientExample requirementClientExample = new RequirementClientExample();
        RequirementClientExample.Criteria criteriaClient = requirementClientExample.createCriteria();
        criteriaClient.andRequirementIdEqualTo(requirement.getId());
        List<RequirementClientKey> requirementClientKeyList = this.requirementClientMapper
                .selectByExample(requirementClientExample);
        if (requirementClientKeyList == null || requirementClientKeyList.isEmpty()) {
            throw new Exception("Client not found.");
        }

        // 提取数组 -- client
        String[] clientIdArr = requirementClientKeyList.stream()
                .map(RequirementClientKey::getClientId)
                .toArray(String[]::new);

        // 条件查询 -- backlogitem
        RequirementBacklogitemExample requirementBacklogitemExample = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteriaBacklogItem = requirementBacklogitemExample.createCriteria();
        criteriaBacklogItem.andRequirementIdEqualTo(requirement.getId());
        List<RequirementBacklogitemKey> requirementBacklogitemKeyList = this.requirementBacklogitemMapper
                .selectByExample(requirementBacklogitemExample);
        if (requirementBacklogitemKeyList == null || requirementBacklogitemKeyList.isEmpty()) {
            throw new Exception("Backlogitem not found.");
        }

        // 提取数组 -- backlogitem
        String[] backlogItemIdArr = requirementBacklogitemKeyList.stream()
                .map(RequirementBacklogitemKey::getBacklogitemId)
                .toArray(String[]::new);

        // 条件查询 -- version
        RequirementVersionExample requirementVersionExample = new RequirementVersionExample();
        RequirementVersionExample.Criteria criteriaVersion = requirementVersionExample.createCriteria();
        criteriaVersion.andRequirementIdEqualTo(requirement.getId());
        List<RequirementVersion> requirementVersionKeyList = this.requirementVersionMapper
                .selectByExample(requirementVersionExample);
        if (requirementVersionKeyList == null || requirementVersionKeyList.isEmpty()) {
            throw new Exception("Version not found.");
        }

        // 创建聚合数组 -- version
        List<VersionAggregation> versionList = new ArrayList<>();
        for (RequirementVersion requirementVersion : requirementVersionKeyList) {
            VersionAggregation version = VersionAggregation.createVersionByAll(
                    requirementVersion.getId(),
                    requirementVersion.getName(),
                    requirementVersion.getDetail(),
                    requirementVersion.getCreateTime());
            versionList.add(version);
        }
        VersionAggregation[] versionArr = versionList.toArray(new VersionAggregation[0]);

        // 聚合
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

    public void insert(RequirementAggregation requirementAggregation) throws Exception {
        // 插入 requirement
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

        // 插入 client
        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey.setRequirementId(requirement.getId());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new Exception("Insert client failed.");
            }
        }

        // 插入 backlogitem
        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey.setRequirementId(requirement.getId());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new Exception("Insert backlogitem failed.");
            }
        }

        // 插入 version
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
        // 更新 requirement
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

        // 关联表通常都是先删除旧的，再插入新的，因为都是主键
        // 删除旧的 client 关联
        RequirementClientExample requirementClientExample = new RequirementClientExample();
        RequirementClientExample.Criteria criteriaClient = requirementClientExample.createCriteria();
        criteriaClient.andRequirementIdEqualTo(requirement.getId());
        this.requirementClientMapper.deleteByExample(requirementClientExample);

        // 插入新的 client 关联
        for (String clientId : requirementAggregation.getClientIDArr()) {
            RequirementClientKey requirementClientKey = new RequirementClientKey();
            requirementClientKey.setRequirementId(requirement.getId());
            requirementClientKey.setClientId(clientId);
            int clientResult = this.requirementClientMapper.insert(requirementClientKey);
            if (clientResult <= 0) {
                throw new Exception("Insert client failed.");
            }
        }

        // 删除旧的 backlogitem 关联
        RequirementBacklogitemExample requirementBacklogitemExample = new RequirementBacklogitemExample();
        RequirementBacklogitemExample.Criteria criteriaBacklogItem = requirementBacklogitemExample.createCriteria();
        criteriaBacklogItem.andRequirementIdEqualTo(requirement.getId());
        this.requirementBacklogitemMapper.deleteByExample(requirementBacklogitemExample);

        // 插入新的 backlogitem 关联
        for (String backlogItemId : requirementAggregation.getBacklogItemIDArr()) {
            RequirementBacklogitemKey requirementBacklogitemKey = new RequirementBacklogitemKey();
            requirementBacklogitemKey.setRequirementId(requirement.getId());
            requirementBacklogitemKey.setBacklogitemId(backlogItemId);
            int backlogItemResult = this.requirementBacklogitemMapper.insert(requirementBacklogitemKey);
            if (backlogItemResult <= 0) {
                throw new Exception("Insert backlogitem failed.");
            }
        }

        // 更新 version
        for (VersionAggregation version : requirementAggregation.getVersionArr()) {
            RequirementVersion requirementVersion = new RequirementVersion();
            String versionId = version.getId();
            requirementVersion.setId(versionId);
            requirementVersion.setRequirementId(requirement.getId());
            int versionResult = this.requirementVersionMapper.updateByPrimaryKey(requirementVersion);
            if (versionResult <= 0) {
                throw new Exception("Update version failed.");
            }
        }
    }
}

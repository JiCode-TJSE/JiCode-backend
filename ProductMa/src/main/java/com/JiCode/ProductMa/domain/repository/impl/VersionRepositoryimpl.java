package com.JiCode.ProductMa.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementVersionMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.repository.VersionRepository;

@Repository
public class VersionRepositoryimpl implements VersionRepository {
    @Autowired
    private RequirementVersionMapper requirementVersionMapper;

    public void insert(VersionAggregation versionAggregation) throws Exception {
        RequirementVersion record = new RequirementVersion();
        record.setId(versionAggregation.getId());
        record.setName(versionAggregation.getName());
        record.setDetail(versionAggregation.getDescription());
        record.setCreateTime(versionAggregation.getCreateTime());
        int result = requirementVersionMapper.insert(record);
        if (result <= 0) {
            throw new Exception("Insert version failed.");
        }
    }

    public void delete(String id) throws Exception {
        int result = requirementVersionMapper.deleteByPrimaryKey(id);
        if (result <= 0) {
            throw new Exception("Delete version failed.");
        }
    }

    public void update(VersionAggregation versionAggregation) throws Exception {
        RequirementVersion record = new RequirementVersion();
        record.setId(versionAggregation.getId());
        record.setName(versionAggregation.getName());
        record.setDetail(versionAggregation.getDescription());
        record.setCreateTime(versionAggregation.getCreateTime());
        int result = requirementVersionMapper.updateByPrimaryKey(record);
        if (result <= 0) {
            throw new Exception("Update version failed.");
        }
    }

    public VersionAggregation selectById(String id) throws Exception {
        RequirementVersion record = requirementVersionMapper.selectByPrimaryKey(id);
        if (record == null) {
            throw new Exception("Select version failed.");
        }
        return VersionAggregation.createVersionByAll(record.getId(), record.getName(), record.getDetail(),
                record.getCreateTime());
    }
}
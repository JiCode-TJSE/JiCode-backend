package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementVersionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.repository.VersionRepository;
import com.JiCode.ProductMa.exception.CreateFailedException;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;

@Repository
public class VersionRepositoryimpl implements VersionRepository {
    @Autowired
    private RequirementVersionMapper requirementVersionMapper;

    public String insert(String requirementId, VersionAggregation versionAggregation) throws InsertFailedException {
        RequirementVersion record = new RequirementVersion();
        BeanUtils.copyProperties(versionAggregation, record);
        record.setBelongRequirementId(requirementId);
        int result = requirementVersionMapper.insert(record);
        if (result <= 0) {
            throw new InsertFailedException(
                    "Failed to insert version with id " + versionAggregation.getId() + ".");
        }
        return record.getId();
    }

    public void delete(String id) throws DeleteFailedException {
        int result = requirementVersionMapper.deleteByPrimaryKey(id);
        if (result <= 0) {
            throw new DeleteFailedException("Failed to delete version with id " + id + ".");
        }
    }

    public void update(String belongRequirementId, VersionAggregation versionAggregation) throws UpdateFailedException {
        RequirementVersion record = new RequirementVersion();
        BeanUtils.copyProperties(versionAggregation, record);
        record.setBelongRequirementId(belongRequirementId);
        int result = requirementVersionMapper.updateByPrimaryKey(record);
        if (result <= 0) {
            throw new UpdateFailedException(
                    "Failed to update version with id " + versionAggregation.getId() + ".");
        }
    }

    public VersionAggregation selectById(String id) throws SelectFailedException {
        RequirementVersion record = requirementVersionMapper.selectByPrimaryKey(id);
        if (record == null) {
            throw new SelectFailedException("Failed to select version with id " + id + ".");
        }
        try {
            return VersionAggregation.createVersionByAll(record);
        } catch (CreateFailedException e) {
            throw new SelectFailedException("Failed to create VersionAggregation for version with id " + id + ".", e);
        }
    }
}
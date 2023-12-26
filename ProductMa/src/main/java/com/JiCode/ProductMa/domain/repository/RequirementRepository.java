package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.VersionsEntity;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;

public interface RequirementRepository {
        public void insert(RequirementAggregation requirementAggregation) throws InsertFailedException;

        public void delete(String id) throws DeleteFailedException;

        public void update(RequirementAggregation requirementAggregation) throws UpdateFailedException;

        public RequirementAggregation selectById(String requirementId) throws SelectFailedException;

        public RequirementContentEntity selectRequirementContent(String requirementContentId)
                        throws SelectFailedException;

        public VersionsEntity selectVersions(String requirementId) throws SelectFailedException;

        public RequirementEntity selectRequirement(String requirementId) throws SelectFailedException;

        public RequirementEntity[] selectRequirementsByPage(String productId, int pageNo, int pageSize)
                        throws SelectFailedException;
}
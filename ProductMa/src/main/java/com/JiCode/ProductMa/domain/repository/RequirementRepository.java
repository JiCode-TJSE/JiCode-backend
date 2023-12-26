package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;

public interface RequirementRepository {
        public void insert(RequirementAggregation requirementAggregation) throws InsertFailedException;

        public void delete(String id) throws DeleteFailedException;

        public void update(RequirementAggregation requirementAggregation) throws UpdateFailedException;

        public RequirementAggregation selectById(String id) throws SelectFailedException;

        public RequirementContentEntity selectVersionContent(String versionId) throws SelectFailedException;
}
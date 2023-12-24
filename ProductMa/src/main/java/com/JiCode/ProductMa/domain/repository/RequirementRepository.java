package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;

public interface RequirementRepository {
    public void insert(RequirementAggregation requirementAggregation) throws Exception;

    public void delete(String id) throws Exception;

    public void update(RequirementAggregation requirementAggregation) throws Exception;

    public RequirementAggregation selectById(String id) throws Exception;
}

package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.exception.requirement.repository.*;

public interface RequirementRepository {
    public void insert(RequirementAggregation requirementAggregation)
            throws InsertRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException,
            InsertVersionFailedException;

    public void delete(String id) throws DeleteRequirementFailedException;

    public void update(RequirementAggregation requirementAggregation)
            throws UpdateRequirementFailedException, InsertClientFailedException, InsertBacklogItemFailedException;

    public RequirementAggregation selectById(String id)
            throws RequirementNotFoundException, ClientNotFoundException, BacklogItemNotFoundException,
            VersionNotFoundException;
}
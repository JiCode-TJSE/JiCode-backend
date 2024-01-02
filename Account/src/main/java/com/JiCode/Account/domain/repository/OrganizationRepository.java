package com.JiCode.Account.domain.repository;

import com.JiCode.Account.domain.model.OrganizationAggregation;

public interface OrganizationRepository {
    public OrganizationAggregation selectById(String organizationId);
}

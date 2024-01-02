package com.JiCode.Account.domain.factory;

import com.JiCode.Account.domain.model.OrganizationAggregation;

public interface OrganizationFactory {
    public OrganizationAggregation createOrganization(
            String organizationId,
            String domainName,
            String organizationName,
            Integer headCount
    );
}

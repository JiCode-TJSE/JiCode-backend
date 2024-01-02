package com.JiCode.Account.domain.factory.impl;

import com.JiCode.Account.domain.factory.OrganizationFactory;
import com.JiCode.Account.domain.model.OrganizationAggregation;
import org.springframework.stereotype.Service;

@Service
public class OrganizationFactoryImpl implements OrganizationFactory {
    @Override
    public OrganizationAggregation createOrganization(String organizationId, String domainName, String organizationName, Integer headCount) {
        OrganizationAggregation organizationAggregation = new OrganizationAggregation();
        organizationAggregation.setOrganizationId(organizationId);
        organizationAggregation.setDomainName(domainName);
        organizationAggregation.setOrganizationName(organizationName);
        organizationAggregation.setHeadCount(headCount);
        return organizationAggregation;
    }
}

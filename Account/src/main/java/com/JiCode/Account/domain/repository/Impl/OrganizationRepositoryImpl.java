package com.JiCode.Account.domain.repository.Impl;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Organization;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.OrganizationMapper;
import com.JiCode.Account.domain.factory.OrganizationFactory;
import com.JiCode.Account.domain.model.OrganizationAggregation;
import com.JiCode.Account.domain.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationRepositoryImpl implements OrganizationRepository {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationFactory organizationFactory;

    @Override
    public OrganizationAggregation selectById(String organizationId) {

        try {
            Organization organization = organizationMapper.selectById(organizationId);
            OrganizationAggregation organizationAggregation = organizationFactory.createOrganization(
                    organization.getOrganizationId(),
                    organization.getDomainName(),
                    organization.getOrganizationName(),
                    organization.getHeadCount()
            );
            return organizationAggregation;
        } catch (Exception e) {
            System.out.println(e);
            return null;// 失败返回空
        }
    }
}

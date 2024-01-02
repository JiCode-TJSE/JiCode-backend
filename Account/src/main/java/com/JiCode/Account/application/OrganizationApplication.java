package com.JiCode.Account.application;

import com.JiCode.Account.application.dto.OrganizationDto;
import com.JiCode.Account.domain.model.OrganizationAggregation;
import com.JiCode.Account.domain.repository.OrganizationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationApplication {
    @Autowired
    OrganizationRepository organizationRepository;

    public OrganizationDto selectByOrganizationId(String organizationId) {
        OrganizationAggregation organizationAggregation = new OrganizationAggregation();
        try {
            organizationAggregation = organizationRepository.selectById(organizationId);
            OrganizationDto organizationDto = new OrganizationDto();
            if (organizationAggregation.getOrganizationId() == null) { // not found
                return null;
            }
            BeanUtils.copyProperties(organizationAggregation, organizationDto);
            return organizationDto;
        } catch (Exception e) {
            return null;
        }
    }
}

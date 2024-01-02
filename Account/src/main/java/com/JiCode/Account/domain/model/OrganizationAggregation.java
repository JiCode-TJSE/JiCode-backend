package com.JiCode.Account.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationAggregation {
    private String organizationId;
    private String domainName;
    private Integer headCount;
    private String organizationName;
}

package com.JiCode.Account.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private String organizationId;
    private String domainName;
    private Integer headCount;
    private String organizationName;
}

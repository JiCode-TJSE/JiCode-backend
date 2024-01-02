package com.JiCode.Account.adaptor.output.dataaccess.DBModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private String organizationId;
    private String domainName;
    private String organizationName;
    private Integer headCount;
}

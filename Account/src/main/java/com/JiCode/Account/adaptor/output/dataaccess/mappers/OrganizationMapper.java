package com.JiCode.Account.adaptor.output.dataaccess.mappers;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Organization;
import org.apache.ibatis.annotations.Select;

public interface OrganizationMapper {
    @Select("select organization_id as organizationId, domain_name as domainName, organization_name as organizationName, head_count as headCount" +
            " from organization " +
            " where organization_id = #{organizationId}")
    public Organization selectById(String organizationId);
}

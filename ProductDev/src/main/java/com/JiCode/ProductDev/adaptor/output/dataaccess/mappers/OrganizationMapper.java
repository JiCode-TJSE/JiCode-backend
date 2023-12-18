package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Organization;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    List<Organization> selectAll();

    Organization selectById(String id);

    // 模糊查询
    List<Organization> selectByCondition(@Param("domain_name") String domain_name, @Param("head_count") int head_count);

    void add(Organization organization);
}

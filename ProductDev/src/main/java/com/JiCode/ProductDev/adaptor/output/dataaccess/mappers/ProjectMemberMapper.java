package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ProjectMemberExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ProjectMemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMemberMapper {
    long countByExample(ProjectMemberExample example);

    int deleteByExample(ProjectMemberExample example);

    int deleteByPrimaryKey(ProjectMemberKey key);

    int insert(ProjectMemberKey record);

    int insertSelective(ProjectMemberKey record);

    List<ProjectMemberKey> selectByExample(ProjectMemberExample example);

    int updateByExampleSelective(@Param("record") ProjectMemberKey record, @Param("example") ProjectMemberExample example);

    int updateByExample(@Param("record") ProjectMemberKey record, @Param("example") ProjectMemberExample example);
}
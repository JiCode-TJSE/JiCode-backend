package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ReleaseMember;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ReleaseMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReleaseMemberMapper {
    long countByExample(ReleaseMemberExample example);

    int deleteByExample(ReleaseMemberExample example);

    int insert(ReleaseMember record);

    int insertSelective(ReleaseMember record);

    List<ReleaseMember> selectByExample(ReleaseMemberExample example);

    int updateByExampleSelective(@Param("record") ReleaseMember record, @Param("example") ReleaseMemberExample example);

    int updateByExample(@Param("record") ReleaseMember record, @Param("example") ReleaseMemberExample example);
}
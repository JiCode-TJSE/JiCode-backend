package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.SprintMember;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.SprintMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SprintMemberMapper {
    long countByExample(SprintMemberExample example);

    int deleteByExample(SprintMemberExample example);

    int insert(SprintMember record);

    int insertSelective(SprintMember record);

    List<SprintMember> selectByExample(SprintMemberExample example);

    int updateByExampleSelective(@Param("record") SprintMember record, @Param("example") SprintMemberExample example);

    int updateByExample(@Param("record") SprintMember record, @Param("example") SprintMemberExample example);
}
package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemMemberExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemMemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemMemberMapper {
    long countByExample(BacklogitemMemberExample example);

    int deleteByExample(BacklogitemMemberExample example);

    int deleteByPrimaryKey(BacklogitemMemberKey key);

    int insert(BacklogitemMemberKey record);

    int insertSelective(BacklogitemMemberKey record);

    List<BacklogitemMemberKey> selectByExample(BacklogitemMemberExample example);

    int updateByExampleSelective(@Param("record") BacklogitemMemberKey record, @Param("example") BacklogitemMemberExample example);

    int updateByExample(@Param("record") BacklogitemMemberKey record, @Param("example") BacklogitemMemberExample example);
}
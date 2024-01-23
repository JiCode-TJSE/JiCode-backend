package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemBacklogitem;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemBacklogitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemBacklogitemMapper {
    long countByExample(BacklogitemBacklogitemExample example);

    int deleteByExample(BacklogitemBacklogitemExample example);

    int insert(BacklogitemBacklogitem record);

    int insertSelective(BacklogitemBacklogitem record);

    List<BacklogitemBacklogitem> selectByExample(BacklogitemBacklogitemExample example);

    int updateByExampleSelective(@Param("record") BacklogitemBacklogitem record, @Param("example") BacklogitemBacklogitemExample example);

    int updateByExample(@Param("record") BacklogitemBacklogitem record, @Param("example") BacklogitemBacklogitemExample example);
}
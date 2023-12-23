package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemExample;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemMapper {
    long countByExample(BacklogitemExample example);

    int deleteByExample(BacklogitemExample example);

    int deleteByPrimaryKey(String id);

    int insert(Backlogitem record);

    int insertSelective(Backlogitem record);

    List<Backlogitem> selectByExample(BacklogitemExample example);

    Backlogitem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Backlogitem record, @Param("example") BacklogitemExample example);

    int updateByExample(@Param("record") Backlogitem record, @Param("example") BacklogitemExample example);

    int updateByPrimaryKeySelective(Backlogitem record);

    int updateByPrimaryKey(Backlogitem record);

    Page<Backlogitem> selectByPaging(BacklogitemExample example);
}
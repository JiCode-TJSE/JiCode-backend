package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Workhour;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.WorkhourExample;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface WorkhourMapper {
    long countByExample(WorkhourExample example);

    int deleteByExample(WorkhourExample example);

    int deleteByPrimaryKey(String id);

    int insert(Workhour record);

    int insertSelective(Workhour record);

    List<Workhour> selectByExample(WorkhourExample example);

    Workhour selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Workhour record, @Param("example") WorkhourExample example);

    int updateByExample(@Param("record") Workhour record, @Param("example") WorkhourExample example);

    int updateByPrimaryKeySelective(Workhour record);

    int updateByPrimaryKey(Workhour record);

    Page<Workhour> selectByPaging(WorkhourExample example);
}
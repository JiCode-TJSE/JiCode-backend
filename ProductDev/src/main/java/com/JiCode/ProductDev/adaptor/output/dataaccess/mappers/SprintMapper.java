package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Sprint;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.SprintExample;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface SprintMapper {
    long countByExample(SprintExample example);

    int deleteByExample(SprintExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sprint record);

    int insertSelective(Sprint record);

    List<Sprint> selectByExample(SprintExample example);

    Sprint selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sprint record, @Param("example") SprintExample example);

    int updateByExample(@Param("record") Sprint record, @Param("example") SprintExample example);

    int updateByPrimaryKeySelective(Sprint record);

    int updateByPrimaryKey(Sprint record);

    Page<Sprint> selectByPaging(SprintExample example);
}
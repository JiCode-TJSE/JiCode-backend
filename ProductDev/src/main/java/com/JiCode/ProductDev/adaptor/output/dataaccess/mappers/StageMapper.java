package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Stage;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.StageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StageMapper {
    long countByExample(StageExample example);

    int deleteByExample(StageExample example);

    int deleteByPrimaryKey(String id);

    int insert(Stage record);

    int insertSelective(Stage record);

    List<Stage> selectByExample(StageExample example);

    Stage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Stage record, @Param("example") StageExample example);

    int updateByExample(@Param("record") Stage record, @Param("example") StageExample example);

    int updateByPrimaryKeySelective(Stage record);

    int updateByPrimaryKey(Stage record);
}
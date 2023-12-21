package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Requirement;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementMapper {
    long countByExample(RequirementExample example);

    int deleteByExample(RequirementExample example);

    int deleteByPrimaryKey(String id);

    int insert(Requirement record);

    int insertSelective(Requirement record);

    List<Requirement> selectByExample(RequirementExample example);

    Requirement selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Requirement record, @Param("example") RequirementExample example);

    int updateByExample(@Param("record") Requirement record, @Param("example") RequirementExample example);

    int updateByPrimaryKeySelective(Requirement record);

    int updateByPrimaryKey(Requirement record);
}
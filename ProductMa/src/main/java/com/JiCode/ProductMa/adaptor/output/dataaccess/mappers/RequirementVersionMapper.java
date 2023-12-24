package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementVersionMapper {
    long countByExample(RequirementVersionExample example);

    int deleteByExample(RequirementVersionExample example);

    int deleteByPrimaryKey(String id);

    int insert(RequirementVersion record);

    int insertSelective(RequirementVersion record);

    List<RequirementVersion> selectByExample(RequirementVersionExample example);

    RequirementVersion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RequirementVersion record, @Param("example") RequirementVersionExample example);

    int updateByExample(@Param("record") RequirementVersion record, @Param("example") RequirementVersionExample example);

    int updateByPrimaryKeySelective(RequirementVersion record);

    int updateByPrimaryKey(RequirementVersion record);
}
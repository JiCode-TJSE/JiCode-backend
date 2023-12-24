package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementClientKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementClientMapper {
    long countByExample(RequirementClientExample example);

    int deleteByExample(RequirementClientExample example);

    int deleteByPrimaryKey(RequirementClientKey key);

    int insert(RequirementClientKey record);

    int insertSelective(RequirementClientKey record);

    List<RequirementClientKey> selectByExample(RequirementClientExample example);

    int updateByExampleSelective(@Param("record") RequirementClientKey record, @Param("example") RequirementClientExample example);

    int updateByExample(@Param("record") RequirementClientKey record, @Param("example") RequirementClientExample example);
}
package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.RequirementBacklogitemExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.RequirementBacklogitemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementBacklogitemMapper {
    long countByExample(RequirementBacklogitemExample example);

    int deleteByExample(RequirementBacklogitemExample example);

    int deleteByPrimaryKey(RequirementBacklogitemKey key);

    int insert(RequirementBacklogitemKey record);

    int insertSelective(RequirementBacklogitemKey record);

    List<RequirementBacklogitemKey> selectByExample(RequirementBacklogitemExample example);

    int updateByExampleSelective(@Param("record") RequirementBacklogitemKey record, @Param("example") RequirementBacklogitemExample example);

    int updateByExample(@Param("record") RequirementBacklogitemKey record, @Param("example") RequirementBacklogitemExample example);
}
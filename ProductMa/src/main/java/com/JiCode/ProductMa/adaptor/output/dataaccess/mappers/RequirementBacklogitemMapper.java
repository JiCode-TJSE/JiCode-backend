package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementBacklogitemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RequirementBacklogitemMapper {
    long countByExample(RequirementBacklogitemExample example);

    int deleteByExample(RequirementBacklogitemExample example);

    int deleteByPrimaryKey(RequirementBacklogitemKey key);

    int insert(RequirementBacklogitemKey record);

    int insertSelective(RequirementBacklogitemKey record);

    List<RequirementBacklogitemKey> selectByExampleWithRowbounds(RequirementBacklogitemExample example, RowBounds rowBounds);

    List<RequirementBacklogitemKey> selectByExample(RequirementBacklogitemExample example);

    int updateByExampleSelective(@Param("record") RequirementBacklogitemKey record, @Param("example") RequirementBacklogitemExample example);

    int updateByExample(@Param("record") RequirementBacklogitemKey record, @Param("example") RequirementBacklogitemExample example);
}
package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementContent;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequirementContentMapper {
    long countByExample(RequirementContentExample example);

    int deleteByExample(RequirementContentExample example);

    int deleteByPrimaryKey(String versionContentId);

    int insert(RequirementContent record);

    int insertSelective(RequirementContent record);

    List<RequirementContent> selectByExample(RequirementContentExample example);

    RequirementContent selectByPrimaryKey(String versionContentId);

    int updateByExampleSelective(@Param("record") RequirementContent record, @Param("example") RequirementContentExample example);

    int updateByExample(@Param("record") RequirementContent record, @Param("example") RequirementContentExample example);

    int updateByPrimaryKeySelective(RequirementContent record);

    int updateByPrimaryKey(RequirementContent record);
}
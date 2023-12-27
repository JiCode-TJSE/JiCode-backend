package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Release;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ReleaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReleaseMapper {
    long countByExample(ReleaseExample example);

    int deleteByExample(ReleaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(Release record);

    int insertSelective(Release record);

    List<Release> selectByExample(ReleaseExample example);

    Release selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Release record, @Param("example") ReleaseExample example);

    int updateByExample(@Param("record") Release record, @Param("example") ReleaseExample example);

    int updateByPrimaryKeySelective(Release record);

    int updateByPrimaryKey(Release record);
}
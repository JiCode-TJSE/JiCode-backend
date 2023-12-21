package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.PVersion;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.PVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PVersionMapper {
    long countByExample(PVersionExample example);

    int deleteByExample(PVersionExample example);

    int deleteByPrimaryKey(String id);

    int insert(PVersion record);

    int insertSelective(PVersion record);

    List<PVersion> selectByExample(PVersionExample example);

    PVersion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PVersion record, @Param("example") PVersionExample example);

    int updateByExample(@Param("record") PVersion record, @Param("example") PVersionExample example);

    int updateByPrimaryKeySelective(PVersion record);

    int updateByPrimaryKey(PVersion record);
}
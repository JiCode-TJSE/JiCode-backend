package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemReleaseExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemReleaseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemReleaseMapper {
    long countByExample(BacklogitemReleaseExample example);

    int deleteByExample(BacklogitemReleaseExample example);

    int deleteByPrimaryKey(BacklogitemReleaseKey key);

    int insert(BacklogitemReleaseKey record);

    int insertSelective(BacklogitemReleaseKey record);

    List<BacklogitemReleaseKey> selectByExample(BacklogitemReleaseExample example);

    int updateByExampleSelective(@Param("record") BacklogitemReleaseKey record, @Param("example") BacklogitemReleaseExample example);

    int updateByExample(@Param("record") BacklogitemReleaseKey record, @Param("example") BacklogitemReleaseExample example);
}
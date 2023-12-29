package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemSprintExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemSprintKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemSprintMapper {
    long countByExample(BacklogitemSprintExample example);

    int deleteByExample(BacklogitemSprintExample example);

    int deleteByPrimaryKey(BacklogitemSprintKey key);

    int insert(BacklogitemSprintKey record);

    int insertSelective(BacklogitemSprintKey record);

    List<BacklogitemSprintKey> selectByExample(BacklogitemSprintExample example);

    int updateByExampleSelective(@Param("record") BacklogitemSprintKey record, @Param("example") BacklogitemSprintExample example);

    int updateByExample(@Param("record") BacklogitemSprintKey record, @Param("example") BacklogitemSprintExample example);
}
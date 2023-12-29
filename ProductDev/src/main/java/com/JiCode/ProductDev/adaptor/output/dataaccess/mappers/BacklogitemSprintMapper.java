package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemSprint;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.BacklogitemSprintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BacklogitemSprintMapper {
    long countByExample(BacklogitemSprintExample example);

    int deleteByExample(BacklogitemSprintExample example);

    int insert(BacklogitemSprint record);

    int insertSelective(BacklogitemSprint record);

    List<BacklogitemSprint> selectByExample(BacklogitemSprintExample example);

    int updateByExampleSelective(@Param("record") BacklogitemSprint record, @Param("example") BacklogitemSprintExample example);

    int updateByExample(@Param("record") BacklogitemSprint record, @Param("example") BacklogitemSprintExample example);
}
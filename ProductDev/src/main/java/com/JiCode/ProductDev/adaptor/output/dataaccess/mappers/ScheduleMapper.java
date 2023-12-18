package com.JiCode.ProductDev.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleMapper {
    @Select("select * from schedule")
    List<Schedule> selectAll();
}

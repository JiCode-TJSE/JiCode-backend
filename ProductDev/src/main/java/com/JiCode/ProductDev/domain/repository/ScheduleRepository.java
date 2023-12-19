package com.JiCode.ProductDev.domain.repository;

import java.util.List;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class ScheduleRepository {

    @Autowired
    private final ScheduleMapper scheduleMapper;

    public ScheduleRepository(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

//    public List<Schedule> getAllSchedules() {
//        return scheduleMapper.selectByExample(null);
//    }

    public ScheduleAggregation selectById(String id){
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        return ScheduleAggregation.ScheduleCreator(schedule);
    }
}
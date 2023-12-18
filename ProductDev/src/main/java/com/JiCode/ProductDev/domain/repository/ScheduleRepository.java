package com.JiCode.ProductDev.domain.repository;

import java.util.List;

import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import org.springframework.stereotype.Repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;

@Repository
public class ScheduleRepository {

    private final ScheduleMapper scheduleMapper;

    public ScheduleRepository(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleMapper.selectAll();
    }

    public ScheduleAggregation selectById(String id){
        Schedule schedule = scheduleMapper.selectById(id);
        return ScheduleAggregation.ScheduleCreator(schedule);
    }
}
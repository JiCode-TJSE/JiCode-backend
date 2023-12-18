package com.JiCode.ProductDev.domain.repository;

import java.util.List;

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
}
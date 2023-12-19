package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        System.out.println(schedule.getEstimatedWorkhour());
        return ScheduleAggregation.ScheduleCreator(schedule);
    }
}
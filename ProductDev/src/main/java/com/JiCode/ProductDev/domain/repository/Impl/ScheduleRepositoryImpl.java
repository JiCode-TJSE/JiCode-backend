package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    @Autowired
    ScheduleMapper scheduleMapper;

    public ScheduleAggregation selectById(String id){
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        System.out.println(schedule.getEstimatedWorkhour());
        return ScheduleAggregation.createSchdule(schedule);
    }
}

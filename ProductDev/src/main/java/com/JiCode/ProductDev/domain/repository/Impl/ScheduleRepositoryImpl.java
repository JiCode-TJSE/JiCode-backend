package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("ScheduleRepository")
public class ScheduleRepositoryImpl implements ScheduleRepository {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    ScheduleFactory scheduleFactory;

    public ScheduleAggregation selectById(String id){
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        return scheduleFactory.createSchedule(schedule.getId(), schedule.getEstimatedWorkhour(),schedule.getActualWorkhour(),schedule.getRemainWorkhour(),schedule.getProgress());
    }
}

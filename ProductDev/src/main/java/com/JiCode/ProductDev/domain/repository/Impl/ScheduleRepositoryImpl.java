package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Workhour;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.WorkhourMapper;
import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
import com.JiCode.ProductDev.domain.factory.WorkhourFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ScheduleRepository")
public class ScheduleRepositoryImpl implements ScheduleRepository {
    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    WorkhourMapper workhourMapper;
    @Autowired
    ScheduleFactory scheduleFactory;

    @Autowired
    WorkhourFactory workhourFactory;

    public ScheduleAggregation selectById(String id) throws SelectFailureException {
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        return scheduleFactory.createSchedule(schedule.getId(), schedule.getEstimatedWorkhour(),schedule.getActualWorkhour(),schedule.getRemainWorkhour(),schedule.getProgress());
    }

    public List<WorkhourAggregation> selectAll(){
        List<Workhour> workhours = workhourMapper.selectByExample(null);
        List<WorkhourAggregation> workhourAggregations = new ArrayList<>();
        for(Workhour workhour:workhours){
            WorkhourAggregation workhourAggregation = workhourFactory.createWorkhour(workhour.getId(), workhour.getHours(), workhour.getDate(), workhour.getType(), workhour.getDetail(), workhour.getScheduleId());
            workhourAggregations.add(workhourAggregation);
        }
        return workhourAggregations;
    }

    public int insert(ScheduleAggregation scheduleAggregation) throws SelectFailureException {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleAggregation.getId());
        schedule.setEstimatedWorkhour(scheduleAggregation.getEstimatedWorkhour());
        schedule.setActualWorkhour(scheduleAggregation.getActualWorkhour());
        schedule.setRemainWorkhour(scheduleAggregation.getRemainWorkhour());
        schedule.setProgress(scheduleAggregation.getProgress());
        return scheduleMapper.insert(schedule);
    }

    public int update(ScheduleAggregation scheduleAggregation){
        Schedule schedule = new Schedule();
        schedule.setId(scheduleAggregation.getId());
        schedule.setEstimatedWorkhour(scheduleAggregation.getEstimatedWorkhour());
        schedule.setActualWorkhour(scheduleAggregation.getActualWorkhour());
        schedule.setRemainWorkhour(scheduleAggregation.getRemainWorkhour());
        schedule.setProgress(scheduleAggregation.getProgress());
        return scheduleMapper.updateByPrimaryKey(schedule);
    }
}

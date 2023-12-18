package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAggregation {
    @Autowired
    ScheduleRepository scheduleRepository;
    public String id;
    public Integer estimated_workhour;
    public Integer actual_workhour;
    public Integer remain_workhour;
    public float progress;

    public ScheduleAggregation() {
    }

    static public ScheduleAggregation ScheduleCreator(Schedule schedule){
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        scheduleAggregation.id = schedule.getId();
        scheduleAggregation.estimated_workhour = schedule.getEstimated_workhour();
        scheduleAggregation.actual_workhour = schedule.getActual_workhour();
        scheduleAggregation.remain_workhour = schedule.getRemain_workhour();
        scheduleAggregation.progress = schedule.getProgress();
        return scheduleAggregation;
    }

}

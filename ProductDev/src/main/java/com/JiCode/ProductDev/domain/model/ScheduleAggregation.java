package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAggregation {
    @Autowired
    ScheduleRepository scheduleRepository;

    public String id;
    public Integer estimatedWorkhour;
    public Integer actualWorkhour;
    public Integer remainWorkhour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEstimatedWorkhour() {
        return estimatedWorkhour;
    }

    public void setEstimatedWorkhour(Integer estimatedWorkhour) {
        this.estimatedWorkhour = estimatedWorkhour;
    }

    public Integer getActualWorkhour() {
        return actualWorkhour;
    }

    public void setActualWorkhour(Integer actualWorkhour) {
        this.actualWorkhour = actualWorkhour;
    }

    public Integer getRemainWorkhour() {
        return remainWorkhour;
    }

    public void setRemainWorkhour(Integer remainWorkhour) {
        this.remainWorkhour = remainWorkhour;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float progress;

    public ScheduleAggregation() {
    }

    static public ScheduleAggregation ScheduleCreator(Schedule schedule){
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        BeanUtils.copyProperties(schedule, scheduleAggregation);
        System.out.println("aggregate:"+scheduleAggregation.id);
        return scheduleAggregation;
    }

}

package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class ScheduleAggregation {
    @Autowired
    ScheduleRepository scheduleRepository;

    public String id;
    public Integer estimatedWorkhour;
    public Integer actualWorkhour;
    public Integer remainWorkhour;
    public float progress;

    public ScheduleAggregation() {
    }

    static public ScheduleAggregation createSchdule(Schedule schedule){
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        BeanUtils.copyProperties(schedule, scheduleAggregation);
        System.out.println(scheduleAggregation);
        return scheduleAggregation;
    }

}

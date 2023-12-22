package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class ScheduleAggregation {
    @Autowired
    ScheduleRepository scheduleRepository;

    private String id;
    private Integer estimatedWorkhour;
    private Integer actualWorkhour;
    private Integer remainWorkhour;
    private float progress;

    // 工厂模式
    static public ScheduleAggregation createSchedule(String id, int estimatedWorkhour, int actualWorkhour,int remainWorkhour, float progress){
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        scheduleAggregation.id = id;
        scheduleAggregation.estimatedWorkhour = estimatedWorkhour;
        scheduleAggregation.actualWorkhour = actualWorkhour;
        scheduleAggregation.remainWorkhour = remainWorkhour;
        scheduleAggregation.progress = progress;
        System.out.println(scheduleAggregation);
        return scheduleAggregation;
    }

}

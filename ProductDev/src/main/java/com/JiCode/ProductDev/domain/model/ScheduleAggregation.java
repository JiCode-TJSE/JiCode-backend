package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class ScheduleAggregation {
    @Autowired
    ScheduleRepository scheduleRepository;

    public String id;
    public Integer estimatedWorkhour;
    public Integer actualWorkhour;
    public Integer remainWorkhour;
    public float progress;

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

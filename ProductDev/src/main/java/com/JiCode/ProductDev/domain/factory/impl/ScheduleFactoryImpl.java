package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import org.springframework.stereotype.Service;

/**
 * 工厂类实现
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service
public class ScheduleFactoryImpl implements ScheduleFactory {
    public ScheduleAggregation createSchedule(String id, int estimatedWorkhour, int actualWorkhour,int remainWorkhour, float progress){
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        scheduleAggregation.setId(id);
        scheduleAggregation.setEstimatedWorkhour(estimatedWorkhour);
        scheduleAggregation.setActualWorkhour(actualWorkhour);
        scheduleAggregation.setRemainWorkhour(remainWorkhour);
        scheduleAggregation.setProgress(progress);
        System.out.println(scheduleAggregation);
        return scheduleAggregation;
    }
}

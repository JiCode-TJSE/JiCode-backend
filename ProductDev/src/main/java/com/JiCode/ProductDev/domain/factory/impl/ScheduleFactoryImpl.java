package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.domain.repository.WorkhourRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工厂类实现
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service
public class ScheduleFactoryImpl implements ScheduleFactory {
    @Autowired
    WorkhourRepository workhourRepository;
    public ScheduleAggregation createSchedule(String id, int estimatedWorkhour, int actualWorkhour,int remainWorkhour, float progress) throws SelectFailureException {
        ScheduleAggregation scheduleAggregation = new ScheduleAggregation();
        scheduleAggregation.setId(id);
        scheduleAggregation.setEstimatedWorkhour(estimatedWorkhour);
        scheduleAggregation.setActualWorkhour(actualWorkhour);
        scheduleAggregation.setRemainWorkhour(remainWorkhour);
        scheduleAggregation.setProgress(progress);
        System.out.println(scheduleAggregation);

        // 子聚合根
        List<WorkhourAggregation> workhourAggregation = workhourRepository.selectBySchedule(id);
        scheduleAggregation.setWorkhourAggregation(workhourAggregation);

        return scheduleAggregation;
    }
}

package com.JiCode.ProductDev.domain.factory;

import com.JiCode.ProductDev.domain.model.ScheduleAggregation;

public interface ScheduleFactory {
    public ScheduleAggregation createSchedule(String id, int estimatedWorkhour, int actualWorkhour, int remainWorkhour, float progress);
}

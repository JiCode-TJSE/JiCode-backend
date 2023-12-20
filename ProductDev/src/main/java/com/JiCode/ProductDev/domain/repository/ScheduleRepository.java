package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ScheduleAggregation;


public interface ScheduleRepository {
    public ScheduleAggregation selectById(String id);
}
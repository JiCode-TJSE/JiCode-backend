package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;


public interface ScheduleRepository {
    public ScheduleAggregation selectById(String id) throws SelectFailureException;
}
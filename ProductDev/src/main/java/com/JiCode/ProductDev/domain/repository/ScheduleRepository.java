package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;

import java.util.List;


public interface ScheduleRepository {
    public ScheduleAggregation selectById(String id) throws SelectFailureException;

    public List<WorkhourAggregation> selectAll();

    public int insert(ScheduleAggregation scheduleAggregation) throws SelectFailureException;

    public int update(ScheduleAggregation scheduleAggregation);
}
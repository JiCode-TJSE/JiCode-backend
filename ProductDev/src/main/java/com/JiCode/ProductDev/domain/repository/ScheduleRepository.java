package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Schedule;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ScheduleMapper;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public interface ScheduleRepository {
    public ScheduleAggregation selectById(String id);
}
package com.JiCode.ProductDev.domain.factory;

import com.JiCode.ProductDev.domain.model.WorkhourAggregation;

import java.util.Date;

public interface WorkhourFactory {
    public WorkhourAggregation createWorkhour(String id, String hours, Date date, String type, String detail, String scheduleId);


}

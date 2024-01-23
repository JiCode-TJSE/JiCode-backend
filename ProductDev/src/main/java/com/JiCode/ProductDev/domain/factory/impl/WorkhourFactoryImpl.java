package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.domain.factory.WorkhourFactory;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkhourFactoryImpl implements WorkhourFactory {
    public WorkhourAggregation createWorkhour(String id, String hours, Date date, String type, String detail, String scheduleId) {
        WorkhourAggregation workhourAggregation = new WorkhourAggregation();
        workhourAggregation.setId(id);
        workhourAggregation.setHours(hours);
        workhourAggregation.setDate(date);
        workhourAggregation.setType(type);
        workhourAggregation.setDetail(detail);
        workhourAggregation.setScheduleId(scheduleId);

        return workhourAggregation;
    }
}

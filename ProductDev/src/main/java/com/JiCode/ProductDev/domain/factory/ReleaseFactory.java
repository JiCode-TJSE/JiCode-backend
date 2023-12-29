package com.JiCode.ProductDev.domain.factory;

import com.JiCode.ProductDev.domain.model.ReleaseAggregation;

import java.util.Date;
import java.util.List;
public interface ReleaseFactory {
    public ReleaseAggregation createRelease(String id, Date startTime, Date endTime, String type, String projectid, String managerId, List<String> memberIds, String topic, String stage);
}

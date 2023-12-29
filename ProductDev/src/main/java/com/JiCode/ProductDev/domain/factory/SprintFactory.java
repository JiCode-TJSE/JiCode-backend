package com.JiCode.ProductDev.domain.factory;

import com.JiCode.ProductDev.domain.model.SprintAggregation;

import java.util.Date;
import java.util.List;

public interface SprintFactory {
    public SprintAggregation createSprint(String id, Date startTime, Date endTime, String goal, String type, String projectId, String managerId, String releaseId, List<String> memberIds, String topic);

}

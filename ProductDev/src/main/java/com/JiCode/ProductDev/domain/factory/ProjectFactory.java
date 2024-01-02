package com.JiCode.ProductDev.domain.factory;

import java.util.Date;
import java.util.List;

import com.JiCode.ProductDev.domain.model.ProjectAggregation;

public interface ProjectFactory {
    public ProjectAggregation createProject(String id, String status, Float progress, Date startTime, Date endTime, String managerId, List<String>members, String topic, String organizationId,String description);

}

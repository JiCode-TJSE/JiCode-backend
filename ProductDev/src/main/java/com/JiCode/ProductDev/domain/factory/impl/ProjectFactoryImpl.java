package com.JiCode.ProductDev.domain.factory.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.domain.factory.ProjectFactory;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;

/**
 * 工厂类实现
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service
public class ProjectFactoryImpl implements ProjectFactory {


    public ProjectAggregation createProject(String id, String status, Float progress, Date startTime, Date endTime, String managerId, List<String>members, String topic, String organizationId, String description) {
        ProjectAggregation projectAggregation = new ProjectAggregation();
        projectAggregation.setId(id);
        projectAggregation.setStatus(status);
        projectAggregation.setProgress(progress);
        projectAggregation.setStartTime(startTime);
        projectAggregation.setEndTime(endTime);
        projectAggregation.setManagerId(managerId);
        projectAggregation.setMember(members);
        projectAggregation.setTopic(topic);
        projectAggregation.setOrganizationId(organizationId);
        projectAggregation.setDescription(description);
        System.out.println(projectAggregation);
        return projectAggregation;
    }
}

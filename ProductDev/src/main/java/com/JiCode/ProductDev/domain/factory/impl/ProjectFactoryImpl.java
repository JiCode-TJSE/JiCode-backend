package com.JiCode.ProductDev.domain.factory.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.domain.factory.ProjectFactory;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;

@Service
public class ProjectFactoryImpl implements ProjectFactory {
    @Autowired
    ProjectRepository projectRepository;

    public ProjectAggregation createProject(String id, String status, float progress, Date startTime, Date endTime, String managerId, List<String>members){
        ProjectAggregation projectAggregation = new ProjectAggregation();
        projectAggregation.setId(id);
        projectAggregation.setStatus(status);
        projectAggregation.setProgress(progress);
        projectAggregation.setStartTime(startTime);
        projectAggregation.setEndTime(endTime);
        projectAggregation.setManagerId(managerId);
        projectAggregation.setMember(members);
        System.out.println(projectAggregation);
        return projectAggregation;
    }
}

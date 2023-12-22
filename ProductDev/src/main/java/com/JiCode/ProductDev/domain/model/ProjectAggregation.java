package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@NoArgsConstructor
public class ProjectAggregation {
    @Autowired
    ProjectRepository projectRepository;

    private String id;

    private String status;

    private Float progress;

    private Date startTime;

    private Date endTime;

    private String managerId;

    // 工厂模式
    static public ProjectAggregation createProject(String id, String status, float progress, Date startTime, Date endTime, String managerId){
        ProjectAggregation projectAggregation = new ProjectAggregation();
        projectAggregation.id = id;
        projectAggregation.status = status;
        projectAggregation.progress = progress;
        projectAggregation.startTime = startTime;
        projectAggregation.endTime = endTime;
        projectAggregation.managerId = managerId;
        System.out.println(projectAggregation);
        return projectAggregation;
    }
}

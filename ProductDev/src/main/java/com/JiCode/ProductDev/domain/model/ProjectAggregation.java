package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Data
@NoArgsConstructor
public class ProjectAggregation {
    @Autowired
    ProjectRepository projectRepository;

    private String id;

    String status;

    private Float progress;

    private Date startTime;

    private Date endTime;

    private String managerId;

    private List<String> member;

    public List<String> getMember() {
        return member;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Float getProgress() {
        return progress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getManagerId() {
        return managerId;
    }

    // 工厂模式
    static public ProjectAggregation createProject(String id, String status, float progress, Date startTime, Date endTime, String managerId, List<String>members){
        ProjectAggregation projectAggregation = new ProjectAggregation();
        projectAggregation.id = id;
        projectAggregation.status = status;
        projectAggregation.progress = progress;
        projectAggregation.startTime = startTime;
        projectAggregation.endTime = endTime;
        projectAggregation.managerId = managerId;
        projectAggregation.member = members;
        System.out.println(projectAggregation);
        return projectAggregation;
    }
}

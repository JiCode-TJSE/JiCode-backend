package com.JiCode.ProductDev.domain.model;


import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@NoArgsConstructor
public class BacklogItemAggregation {
    @Autowired
    BacklogItemRepository backlogItemRepository;

    private String id;

    private String priority;

    private Date startTime;

    private Date endTime;

    private String source;

    private String type;

    private String description;

    private String projectId;

    private String managerId;

    private String scheduleId;

    public String getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    static public BacklogItemAggregation createBacklogItem(String id, String priority, Date startTime, Date endTime, String source, String type, String description, String projectId, String managerId, String scheduleId){
        BacklogItemAggregation backlogItemAggregation = new BacklogItemAggregation();
        backlogItemAggregation.id = id;
        backlogItemAggregation.priority = priority;
        backlogItemAggregation.startTime = startTime;
        backlogItemAggregation.endTime = endTime;
        backlogItemAggregation.source = source;
        backlogItemAggregation.type = type;
        backlogItemAggregation.description = description;
        backlogItemAggregation.projectId = projectId;
        backlogItemAggregation.managerId = managerId;
        backlogItemAggregation.scheduleId = scheduleId;
        System.out.println(backlogItemAggregation);
        return backlogItemAggregation;
    }


}

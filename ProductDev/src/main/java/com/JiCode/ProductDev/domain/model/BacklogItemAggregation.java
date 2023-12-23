package com.JiCode.ProductDev.domain.model;


import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import lombok.Data;
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

package com.JiCode.ProductDev.domain.model;


import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.Impl.ScheduleRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Laurent Wu
 * @date 2023/12/24
 */
@Data
@NoArgsConstructor
@Component
public class BacklogItemAggregation {
    @Autowired
    BacklogItemRepository backlogItemRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    // backlogitem 的属性
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

    // backlogitem_member 联系集当中的属性，在这里体现为一个列表
    private List<String> memberIds;

    private ScheduleAggregation scheduleAggregation;


    public List<String> getMembers() {
        return memberIds;
    }

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
    // 工厂模式

    static public BacklogItemAggregation createBacklogItem(String id, String priority, Date startTime, Date endTime, String source, String type, String description, String projectId, String managerId, String scheduleId, List<String> memberIds){
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
        backlogItemAggregation.memberIds = memberIds;

        // 这里把scheduleAggregation select出来并且加入到聚合当中
        System.out.println("scheduleRepository: " + backlogItemAggregation.scheduleRepository);
        if(backlogItemAggregation.scheduleRepository == null){
            backlogItemAggregation.scheduleAggregation = null;
        }
        else{
            backlogItemAggregation.scheduleAggregation = backlogItemAggregation.scheduleRepository.selectById(scheduleId);
        }
        backlogItemAggregation.scheduleAggregation = backlogItemAggregation.scheduleRepository.selectById(scheduleId);
        return backlogItemAggregation;
    }

}

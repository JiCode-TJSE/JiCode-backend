package com.JiCode.ProductDev.domain.model;


import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.Impl.ScheduleRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Data
@NoArgsConstructor
public class BacklogItemAggregation {
    @Autowired
    BacklogItemRepository backlogItemRepository;

    // 静态字段不能通过Spring的依赖注入，因为spring的依赖注入是基于实例的，而静态字段是基于类的
    // 这里使用一个非静态的setter方法，在这个方法中将bean赋值给静态字段
    @Autowired
    public void setScheduleRepositoryImpl(ScheduleRepositoryImpl scheduleRepositoryImpl) {
        BacklogItemAggregation.scheduleRepositoryImpl = scheduleRepositoryImpl;
    }

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

    private static ScheduleAggregation scheduleAggregation;

    private static ScheduleRepositoryImpl scheduleRepositoryImpl;

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
        // 注意依赖注入的方式
        scheduleAggregation = scheduleRepositoryImpl.selectById(scheduleId);

        System.out.println(backlogItemAggregation);
        return backlogItemAggregation;
    }

}

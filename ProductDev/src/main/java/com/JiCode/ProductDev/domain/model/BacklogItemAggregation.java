package com.JiCode.ProductDev.domain.model;


import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.Impl.ScheduleRepositoryImpl;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class BacklogItemAggregation {

    public void setId(String id) {
        this.id = id;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
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

    private String topic;
    private String status;

    private String organizationId;
    private String projectTopic;
    // backlogitem_member 联系集当中的属性，在这里体现为一个列表
    private List<String> memberIds;

    private List<String> sprintIds;

    private List<String> releaseIds;

    private List<String> backlogitemIds;

    public List<String> getBacklogitemIds() {
        return backlogitemIds;
    }

    public void setBacklogitemIds(List<String> backlogitemIds) {
        this.backlogitemIds = backlogitemIds;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


    public String getProjectTopic() {
        return projectTopic;
    }

    public void setProjectTopic(String projectTopic) {
        this.projectTopic = projectTopic;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }



    //getter

    public List<String> getReleaseIds() {
        return releaseIds;
    }

    public void setReleaseIds(List<String> releaseIds) {
        this.releaseIds = releaseIds;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }



    public List<String> getSprintIds() {
        return sprintIds;
    }

    public void setSprintIds(List<String> sprintIds) {
        this.sprintIds = sprintIds;
    }



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
}

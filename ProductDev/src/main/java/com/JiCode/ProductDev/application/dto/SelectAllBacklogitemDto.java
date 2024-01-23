package com.JiCode.ProductDev.application.dto;

import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class SelectAllBacklogitemDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    public List<String> getSprintIds() {
        return sprintIds;
    }

    public void setSprintIds(List<String> sprintIds) {
        this.sprintIds = sprintIds;
    }

    public List<String> getReleaseIds() {
        return releaseIds;
    }

    public void setReleaseIds(List<String> releaseIds) {
        this.releaseIds = releaseIds;
    }

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

    @Override
    public String toString() {
        return "SelectAllBacklogitemDto{" +
                "id='" + id + '\'' +
                ", priority='" + priority + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", projectId='" + projectId + '\'' +
                ", managerId='" + managerId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", projectTopic='" + projectTopic + '\'' +
                ", memberIds=" + memberIds +
                ", sprintIds=" + sprintIds +
                ", releaseIds=" + releaseIds +
                '}';
    }
}

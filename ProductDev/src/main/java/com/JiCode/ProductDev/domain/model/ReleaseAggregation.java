package com.JiCode.ProductDev.domain.model;

import java.util.Date;
import java.util.List;

public class ReleaseAggregation {
    private String id;

    private Date startTime;

    private Date endTime;

    private String type;

    private String projectId;

    private String managerId;

    List<String> memberIds;
    private String topic;
    private String stage;

    public List<String> getBacklogItemIds() {
        return backlogItemIds;
    }

    public void setBacklogItemIds(List<String> backlogItemIds) {
        this.backlogItemIds = backlogItemIds;
    }

    List<String> backlogItemIds;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }



    public ReleaseAggregation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }
}

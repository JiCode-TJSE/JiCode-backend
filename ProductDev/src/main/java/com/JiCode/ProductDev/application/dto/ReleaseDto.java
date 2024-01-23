package com.JiCode.ProductDev.application.dto;

import com.JiCode.ProductDev.domain.model.value.ReleaseStage;

import java.util.Date;
import java.util.List;

public class ReleaseDto {
    private String id;

    private Date startTime;

    private Date endTime;

    private String type;

    private String projectId;

    private String managerId;

    List<String> memberIds;
    private String topic;
    private String stageId;

    private String stageStatus;

    private String organizationId;

    List<String> backlogItemIds;

    List<ReleaseStage> stages;

    public List<ReleaseStage> getStages() {
        return stages;
    }

    public String getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
    }

    public void setStages(List<ReleaseStage> stages) {
        this.stages = stages;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getBacklogItemIds() {
        return backlogItemIds;
    }

    public void setBacklogItemIds(List<String> backlogItemIds) {
        this.backlogItemIds = backlogItemIds;
    }
}

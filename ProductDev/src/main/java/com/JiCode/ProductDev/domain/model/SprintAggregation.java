package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.domain.repository.SprintRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Data
@NoArgsConstructor
public class SprintAggregation {
    private String id;

    private Date startTime;

    private Date endTime;

    private String goal;

    private String type;

    private String projectId;

    private String managerId;

    private String releaseId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String topic;

    private List<String> memberIds;

    private List<String> backlogItemIds;

    public List<String> getBacklogItemIds() {
        return backlogItemIds;
    }

    public void setBacklogItemIds(List<String> backlogItemIds) {
        this.backlogItemIds = backlogItemIds;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }


    public String getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getGoal() {
        return goal;
    }

    public String getType() {
        return type;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }
}

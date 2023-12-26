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
@Service
@Data
@NoArgsConstructor
public class SprintAggregation {
    @Autowired
    SprintRepository sprintRepository;
    private String id;

    private Date startTime;

    private Date endTime;

    private String goal;

    private String type;

    private String projectId;

    private String managerId;

    private String releaseId;

    private List<String> memberIds;

    static public SprintAggregation createSprint(String id, Date startTime, Date endTime, String goal, String type, String projectId, String managerId, String releaseId, List<String> memberIds){
        SprintAggregation sprintAggregation = new SprintAggregation();
        sprintAggregation.id = id;
        sprintAggregation.startTime = startTime;
        sprintAggregation.endTime = endTime;
        sprintAggregation.goal = goal;
        sprintAggregation.type = type;
        sprintAggregation.projectId = projectId;
        sprintAggregation.managerId = managerId;
        sprintAggregation.releaseId = releaseId;
        sprintAggregation.memberIds = memberIds;
        System.out.println(sprintAggregation);
        return sprintAggregation;
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

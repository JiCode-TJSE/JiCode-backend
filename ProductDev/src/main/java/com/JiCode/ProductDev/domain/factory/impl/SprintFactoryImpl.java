package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 工厂类实现
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service
public class SprintFactoryImpl implements SprintFactory {

    public SprintAggregation createSprint(String id, Date startTime, Date endTime, String goal, String type, String projectId, String managerId, String releaseId, List<String> memberIds, String topic, List<String> backlogItemIds){
        SprintAggregation sprintAggregation = new SprintAggregation();
        sprintAggregation.setId(id);
        sprintAggregation.setStartTime(startTime);
        sprintAggregation.setEndTime(endTime);
        sprintAggregation.setGoal(goal);
        sprintAggregation.setType(type);
        sprintAggregation.setProjectId(projectId);
        sprintAggregation.setManagerId(managerId);
        sprintAggregation.setReleaseId(releaseId);
        sprintAggregation.setMemberIds(memberIds);
        sprintAggregation.setTopic(topic);
        sprintAggregation.setBacklogItemIds(backlogItemIds);
        System.out.println(sprintAggregation);
        return sprintAggregation;
    }
}

package com.JiCode.ProductDev.domain.factory.impl;

import java.util.Date;
import java.util.List;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMapper;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.domain.factory.BacklogItemFactory;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;

/**
 * 工厂类实现
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service
public class BacklogItemFactoryImpl implements BacklogItemFactory {
    @Autowired
    ProjectMapper projectMapper;
    public BacklogItemAggregation createBacklogItem(String id, String priority, Date startTime, Date endTime, String source, String type, String description, String projectId, String managerId, String scheduleId, List<String> memberIds,String topic,List<String>sprintIds, List<String> releaseIds, String status, String organizationId, List<String> backloitemIds){
        BacklogItemAggregation backlogItemAggregation = new BacklogItemAggregation();
        backlogItemAggregation.setId(id);
        backlogItemAggregation.setPriority(priority);
        backlogItemAggregation.setStartTime(startTime);
        backlogItemAggregation.setEndTime(endTime);
        backlogItemAggregation.setSource(source);
        backlogItemAggregation.setType(type);
        backlogItemAggregation.setDescription(description);
        backlogItemAggregation.setProjectId(projectId);
        backlogItemAggregation.setManagerId(managerId);
        backlogItemAggregation.setScheduleId(scheduleId);
        backlogItemAggregation.setManagerId(managerId);
        backlogItemAggregation.setTopic(topic);
        backlogItemAggregation.setStatus(status);
        backlogItemAggregation.setMemberIds(memberIds);
        backlogItemAggregation.setSprintIds(sprintIds);
        backlogItemAggregation.setReleaseIds(releaseIds);
        backlogItemAggregation.setOrganizationId(organizationId);
        backlogItemAggregation.setBacklogitemIds(backloitemIds);

        Project project = projectMapper.selectByPrimaryKey(projectId);
        backlogItemAggregation.setProjectTopic(project.getTopic());
        return backlogItemAggregation;
    }
}

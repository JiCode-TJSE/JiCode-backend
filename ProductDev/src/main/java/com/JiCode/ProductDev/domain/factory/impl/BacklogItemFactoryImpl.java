package com.JiCode.ProductDev.domain.factory.impl;

import java.util.Date;
import java.util.List;

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
    ScheduleRepository scheduleRepository;

    public BacklogItemAggregation createBacklogItem(String id, String priority, Date startTime, Date endTime, String source, String type, String description, String projectId, String managerId, String scheduleId, List<String> memberIds){
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

        // 这里把scheduleAggregation select出来并且加入到聚合当中
        System.out.println("scheduleRepository: " + scheduleRepository);

        ScheduleAggregation scheduleAggregation = scheduleRepository.selectById(scheduleId);

        backlogItemAggregation.setScheduleAggregation(scheduleAggregation);

        return backlogItemAggregation;
    }
}

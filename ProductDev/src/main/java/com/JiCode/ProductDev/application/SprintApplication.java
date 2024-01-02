package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.application.dto.CreateSprintDto;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintApplication {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    SprintFactory sprintFactory;
    @Transactional(readOnly = true)
    public void createSprint(CreateSprintDto createSprintDto) throws InsertFailureException {
        SprintAggregation sprintAggregation=sprintFactory.createSprint(createSprintDto.id,
                createSprintDto.startTime,
                createSprintDto.endTime,
                createSprintDto.goal,
                createSprintDto.type,
                createSprintDto.projectId,
                createSprintDto.managerId,
                createSprintDto.releaseId,
                createSprintDto.memberIds,
                createSprintDto.topic,
                createSprintDto.backlogItemIds,
                createSprintDto.organizationId);

        sprintRepository.insert(sprintAggregation);
    }

    @Transactional(readOnly = true)
    public List<?> selectSprint(String organizationId)
    {
        var allSprints=sprintRepository.selectAll();
        //筛选出allSprint中所有SprintAggregation的organizationId为organizationId的SprintAggregation
        List<SprintAggregation> filteredSprints = allSprints.stream()
                .filter(sprint -> sprint.getOrganizationId().equals(organizationId))
                .collect(Collectors.toList());
        return filteredSprints;

    }








}

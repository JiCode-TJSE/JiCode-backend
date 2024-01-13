package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.application.dto.CreateSprintDto;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
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
    @Transactional(readOnly = false)
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

    public int delete(String sprintId) throws DeleteFailureException {
        return sprintRepository.deleteById(sprintId);
    }

    public CreateSprintDto selectById(String sprintId) throws SelectFailureException {
        SprintAggregation sprintAggregation=sprintRepository.selectById(sprintId);
        CreateSprintDto createSprintDto=new CreateSprintDto();
        BeanUtils.copyProperties(sprintAggregation,createSprintDto);
        return createSprintDto;
    }


    public int update(CreateSprintDto createSprintDto) throws UpdateFaliureException {
        SprintAggregation sprintAggregation = new SprintAggregation();
        SprintAggregation origin = sprintRepository.selectById(createSprintDto.id);
        sprintAggregation.setId(createSprintDto.id==null?origin.getId():createSprintDto.id);
        sprintAggregation.setStartTime(createSprintDto.startTime==null?origin.getStartTime():createSprintDto.startTime);
        sprintAggregation.setEndTime(createSprintDto.endTime==null?origin.getEndTime():createSprintDto.endTime);
        sprintAggregation.setGoal(createSprintDto.goal==null?origin.getGoal():createSprintDto.goal);
        sprintAggregation.setType(createSprintDto.type==null?origin.getType():createSprintDto.type);
        sprintAggregation.setProjectId(createSprintDto.projectId==null?origin.getProjectId():createSprintDto.projectId);
        sprintAggregation.setManagerId(createSprintDto.managerId==null?origin.getManagerId():createSprintDto.managerId);
        sprintAggregation.setReleaseId(createSprintDto.releaseId==null?origin.getReleaseId():createSprintDto.releaseId);
        sprintAggregation.setMemberIds(createSprintDto.memberIds==null?origin.getMemberIds():createSprintDto.memberIds);
        sprintAggregation.setTopic(createSprintDto.topic==null?origin.getTopic():createSprintDto.topic);
        sprintAggregation.setBacklogItemIds(createSprintDto.backlogItemIds==null?origin.getBacklogItemIds():createSprintDto.backlogItemIds);
        sprintAggregation.setOrganizationId(createSprintDto.organizationId==null?origin.getOrganizationId():createSprintDto.organizationId);

        return sprintRepository.updateById(sprintAggregation);
    }
}

package com.JiCode.ProductDev.application;


import com.JiCode.ProductDev.application.dto.CreateProjectDto;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.application.dto.UpdateProjectDto;
import com.JiCode.ProductDev.domain.factory.ProjectFactory;
import com.JiCode.ProductDev.domain.factory.impl.ProjectFactoryImpl;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectApplication {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectFactory projectFactory;

    @Transactional(readOnly = true)
    public List<?> getAllProject(String organization_id) throws SelectFailureException
    {
        var allProjects=projectRepository.selectAll();
        List<ProjectAggregation> filteredProjects = allProjects.stream()
                .filter(project -> project.getOrganizationId().equals(organization_id))
                .collect(Collectors.toList());
        return filteredProjects;

    }


    @Transactional(readOnly = true)
    public void createProject(CreateProjectDto createProjectDto)
    {
        var projectAggregate=projectFactory.createProject(null,null,null,null,null,null,null,createProjectDto.topic,createProjectDto.organizationId,createProjectDto.description);
        projectRepository.insert(projectAggregate);
    }

    @Transactional(readOnly = true)
    public void deleteProject(String projectId)
    {
        projectRepository.deleteById(projectId);
    }

    @Transactional(readOnly = true)
    public ProjectAggregation getProjectInfo(String projectId)
    {
        return projectRepository.selectById(projectId);

    }

    @Transactional(readOnly = true)
    public void updateProject(UpdateProjectDto updateProjectDto)
    {
        var projectAggregation=projectFactory.createProject(updateProjectDto.id,updateProjectDto.status,
                updateProjectDto.progress,updateProjectDto.startTime,updateProjectDto.endTime,
                updateProjectDto.managerId,updateProjectDto.member,updateProjectDto.topic,updateProjectDto.organizationId,updateProjectDto.description);
        projectRepository.updateById(projectAggregation);
    }








}

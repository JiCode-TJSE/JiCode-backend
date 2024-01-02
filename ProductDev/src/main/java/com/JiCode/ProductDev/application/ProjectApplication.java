package com.JiCode.ProductDev.application;


import com.JiCode.ProductDev.adaptor.in.vo.ProjectInfoVo;
import com.JiCode.ProductDev.application.dto.CreateProjectDto;
import com.JiCode.ProductDev.application.dto.SelectScheduleByIdDto;
import com.JiCode.ProductDev.application.dto.UpdateProjectDto;
import com.JiCode.ProductDev.domain.factory.ProjectFactory;
import com.JiCode.ProductDev.domain.factory.impl.ProjectFactoryImpl;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
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

    @Autowired
    BacklogItemRepository backlogItemRepository;


    @Transactional(readOnly = true)
    public List<?> getAllProject(String organization_id) throws SelectFailureException
    {
        var allProjects=projectRepository.selectAll();
        List<ProjectAggregation> filteredProjects = allProjects.stream()
                .filter(project -> project.getOrganizationId().equals(organization_id))
                .collect(Collectors.toList());
        return filteredProjects;

    }


    @Transactional(readOnly = false)
    public void createProject(CreateProjectDto createProjectDto)
    {
        var projectAggregate=projectFactory.createProject(null,null,null,null,null,null,null,createProjectDto.topic,createProjectDto.organizationId,createProjectDto.description);
        projectRepository.insert(projectAggregate);
    }

    @Transactional(readOnly = false)
    public void deleteProject(String projectId)
    {
        projectRepository.deleteById(projectId);
    }

    @Transactional(readOnly = true)
    public ProjectInfoVo getProjectInfo(String projectId)
    {
        var  projectAggregation=projectRepository.selectById(projectId);
        //未开始 进行中 已完成
        var backLogItemAggregates=backlogItemRepository.selectAll();
        var filteredbackLogItemAggregates = backLogItemAggregates.stream()
                .filter(backLogItemAggregate -> backLogItemAggregate.getProjectId().equals(projectId))
                .collect(Collectors.toList());

        Integer not_begin=0;
        Integer in_progress=0;
        Integer finished=0;
        for(var backLogItemAggregate:filteredbackLogItemAggregates)
        {
            if(backLogItemAggregate.getStatus().equals("未开始"))
            {
                not_begin++;
            }
            else if(backLogItemAggregate.getStatus().equals("进行中"))
            {
                in_progress++;
            }
            else if(backLogItemAggregate.getStatus().equals("已完成"))
            {
                finished++;
            }
        }
        return new ProjectInfoVo(projectAggregation,not_begin,in_progress,finished);


    }

    @Transactional(readOnly = false)
    public void updateProject(UpdateProjectDto updateProjectDto)
    {
        var ori_projectAggregation=projectRepository.selectById(updateProjectDto.id);
        if (updateProjectDto.id == null)
            ori_projectAggregation.setId(ori_projectAggregation.getId());
        else
            ori_projectAggregation.setId(updateProjectDto.id);


        if (updateProjectDto.status == null)
            ori_projectAggregation.setStatus(ori_projectAggregation.getStatus());
        else
            ori_projectAggregation.setStatus(updateProjectDto.status);

        if (updateProjectDto.progress == null)
            ori_projectAggregation.setProgress(ori_projectAggregation.getProgress());
        else
            ori_projectAggregation.setProgress(updateProjectDto.progress);

        if (updateProjectDto.startTime == null)
            ori_projectAggregation.setStartTime(ori_projectAggregation.getStartTime());
        else
            ori_projectAggregation.setStartTime(updateProjectDto.startTime);

        if (updateProjectDto.endTime == null)
            ori_projectAggregation.setEndTime(ori_projectAggregation.getEndTime());
        else
            ori_projectAggregation.setEndTime(updateProjectDto.endTime);

        if (updateProjectDto.managerId == null)
            ori_projectAggregation.setManagerId(ori_projectAggregation.getManagerId());
        else
            ori_projectAggregation.setManagerId(updateProjectDto.managerId);


        if (updateProjectDto.member == null)
            ori_projectAggregation.setMember(ori_projectAggregation.getMember());
        else
            ori_projectAggregation.setMember(updateProjectDto.member);

        if (updateProjectDto.topic == null)
            ori_projectAggregation.setTopic(ori_projectAggregation.getTopic());
        else
            ori_projectAggregation.setTopic(updateProjectDto.topic);

        if (updateProjectDto.organizationId == null)
            ori_projectAggregation.setOrganizationId(ori_projectAggregation.getOrganizationId());
        else
            ori_projectAggregation.setOrganizationId(updateProjectDto.organizationId);

        if (updateProjectDto.description == null)
            ori_projectAggregation.setDescription(ori_projectAggregation.getDescription());
        else
            ori_projectAggregation.setDescription(updateProjectDto.description);



        projectRepository.updateById(ori_projectAggregation);
    }








}

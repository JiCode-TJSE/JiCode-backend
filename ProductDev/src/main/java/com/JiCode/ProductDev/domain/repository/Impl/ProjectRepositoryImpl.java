package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMapper;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @Autowired
    ProjectMapper projectMapper;
    public ProjectAggregation selectById(String id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        System.out.println(project.getId());
        return ProjectAggregation.createProject(project.getId(), project.getStatus(),project.getProgress(),project.getStartTime(),project.getEndTime(),project.getManagerId());
    }
}

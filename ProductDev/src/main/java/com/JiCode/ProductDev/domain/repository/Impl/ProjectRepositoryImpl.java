package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMapper;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @Autowired
    ProjectMapper projectMapper;
    public ProjectAggregation selectById(String id) {
       try{
           Project project = projectMapper.selectByPrimaryKey(id);
           System.out.println(project.getId());
           return ProjectAggregation.createProject(project.getId(), project.getStatus(),project.getProgress(),project.getStartTime(),project.getEndTime(),project.getManagerId());
       }catch (Exception e) {
           System.out.println(e);
           return null;
       }
    }

    public PageInfo<ProjectAggregation> selectAll(int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<Project> projects = projectMapper.selectByPaging(null);
            System.out.println(projects);
            List<ProjectAggregation> projectAggregations = new ArrayList<>();
            for (Project project : projects) {
                ProjectAggregation projectAggregation = ProjectAggregation.createProject(project.getId(),project.getStatus(),project.getProgress(), project.getStartTime(),project.getEndTime(),project.getManagerId()); // use builder to create ProjectAggregation
                projectAggregations.add(projectAggregation);
            }
            return new PageInfo<>(projectAggregations);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int insert(String id, String status, Float progress, Date startTime, Date endTime, String managerId){
        try {
            Project project = new Project();
            project.setId(id);
            project.setStatus(status);
            project.setProgress(progress);
            project.setStartTime(startTime);
            project.setEndTime(endTime);
            project.setManagerId(managerId);
            return projectMapper.insert(project);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(String id){
        try {
            Project project = projectMapper.selectByPrimaryKey(id);
            return projectMapper.updateByPrimaryKey(project);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(String id, String status, Float progress, Date startTime, Date endTime, String managerId){
        try{
            Project project = new Project();
            project.setId(id);
            project.setStatus(status);
            project.setProgress(progress);
            project.setStartTime(startTime);
            project.setEndTime(endTime);
            project.setManagerId(managerId);
            return projectMapper.updateByPrimaryKey(project);
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(String id){
        try{
            return projectMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMapper;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
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

    public int insert(ProjectAggregation projectAggregation){
        try {
            Project project = new Project();
            BeanUtils.copyProperties(projectAggregation, project);
            return projectMapper.insert(project);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(ProjectAggregation projectAggregation){
        try{
            Project project = new Project();
            BeanUtils.copyProperties(projectAggregation, project);
            return projectMapper.updateByPrimaryKey(project);
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(ProjectAggregation projectAggregation){
        try{
            return projectMapper.deleteByPrimaryKey(projectAggregation.getId());
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

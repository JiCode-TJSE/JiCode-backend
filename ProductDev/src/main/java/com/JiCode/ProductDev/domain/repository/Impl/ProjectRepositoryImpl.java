package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ProjectMemberExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.ProjectMemberKey;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ProjectMemberMapper;
import com.JiCode.ProductDev.domain.factory.ProjectFactory;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Laurent Wu
 * @date 2023/12/23
 * @description repository的实现类
 */
@Service("ProjectRepository")
public class ProjectRepositoryImpl implements ProjectRepository {
    @Autowired
    ProjectFactory projectFactory;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ProjectMemberMapper projectMemberMapper;


    /**
     * 将实体类及其他信息转换成聚合
     * @param project 实体类
     * @param memberIds 项目成员id列表
     * @return {@link ProjectAggregation}
     */
    private ProjectAggregation entityToAggregate(Project project, List<String> memberIds){
        ProjectAggregation projectAggregation = projectFactory.createProject(project.getId(),project.getStatus(),project.getProgress(), project.getStartTime(),project.getEndTime(),project.getManagerId(),memberIds, project.getTopic(),project.getOrganizationId());
        return projectAggregation;
    }

    /**
     * 将聚合中的信息保存到数据库当中
     * @param projectAggregation 项目聚合
     * @return int
     */
    private int saveAggregate(ProjectAggregation projectAggregation){
        try{
            // 对实体集进行操作
            Project project = new Project();
            BeanUtils.copyProperties(projectAggregation, project);
            int result = projectMapper.updateByPrimaryKey(project);

            // 对联系集进行操作
            // 首先删除联系集中project对应的所有记录
            // 创建一个新的ProjectMemberExample对象
            ProjectMemberExample example = new ProjectMemberExample();
            ProjectMemberExample.Criteria criteria = example.createCriteria();
            criteria.andProjectIdEqualTo(project.getId());
            int rows = projectMemberMapper.deleteByExample(example);
            System.out.println("Deleted rows: " + rows);

            // 再添加当前所有的成员进入
            List<String> memberIds = projectAggregation.getMember();
            ProjectMemberKey projectMember = new ProjectMemberKey();
            for(String memberId:memberIds){
                projectMember.setProjectId(project.getId());
                projectMember.setMemberId(memberId);
                projectMemberMapper.insert(projectMember);
            }

            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }


    public ProjectAggregation selectById(String id) {
       try{
           Project project = projectMapper.selectByPrimaryKey(id);
           ProjectMemberExample example = new ProjectMemberExample();
           example.createCriteria().andProjectIdEqualTo(id);
           List<ProjectMemberKey> projectMemberKeys = projectMemberMapper.selectByExample(example);
           List<String> memberIds = projectMemberKeys.stream().map(ProjectMemberKey::getMemberId).collect(Collectors.toList());
           ProjectAggregation projectAggregation = entityToAggregate(project, memberIds);
           return projectAggregation;
       }catch (Exception e) {
           System.out.println(e);
           return null;
       }
    }

    public PageInfo<ProjectAggregation> getPage(int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<Project> projects = projectMapper.selectByPaging(null);

            System.out.println(projects);
            List<ProjectAggregation> projectAggregations = new ArrayList<>();
            for (Project project : projects) {
                // 获取成员列表
                ProjectMemberExample example = new ProjectMemberExample();
                example.createCriteria().andProjectIdEqualTo(project.getId());
                List<ProjectMemberKey> projectMemberKeys = projectMemberMapper.selectByExample(example);
                List<String> memberIds = projectMemberKeys.stream().map(ProjectMemberKey::getMemberId).collect(Collectors.toList());

                // 工厂模式建立聚合
                ProjectAggregation projectAggregation = projectFactory.createProject(project.getId(),project.getStatus(),project.getProgress(), project.getStartTime(),project.getEndTime(),project.getManagerId(), memberIds, project.getTopic(),project.getOrganizationId());
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
            project.setId(UUID.randomUUID().toString());
            return projectMapper.insert(project);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(ProjectAggregation projectAggregation){
        try{
            return saveAggregate(projectAggregation);
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

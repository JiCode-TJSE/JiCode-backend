package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.*;
import com.JiCode.ProductDev.domain.factory.BacklogItemFactory;
import com.JiCode.ProductDev.domain.factory.ScheduleFactory;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ScheduleAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BacklogItemRepositoryImpl implements BacklogItemRepository {
    // 工厂
    @Autowired
    BacklogItemFactory backlogItemFactory;

    // backlogitem自身
    @Autowired
    BacklogitemMapper backlogitemMapper;
    // backlogitem 与成员的联系集，负责返回一个项目的成员有哪些
    @Autowired
    BacklogitemMemberMapper backlogitemMemberMapper;

    // backlogitem 与 backlogitem的联系集，负责返回一个工作项与哪些工作项关联
    @Autowired
    BacklogitemBacklogitemMapper backlogitemBacklogitemMapper;

    // backlogitem 与 productrequirement的联系集，负责返回一个工作项与哪些产品需求关联
    @Autowired
    RequirementBacklogitemMapper requirementBacklogitemMapper;

    // backlogitem 与 sprint的联系集，负责返回一个工作项与哪些迭代关联
    @Autowired
    BacklogitemSprintMapper backlogitemSprintMapper;

    @Autowired
    BacklogitemReleaseMapper backlogitemReleaseMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ScheduleFactory scheduleFactory;

    @Autowired
    ScheduleRepository scheduleRepository;

    /**
     * 将实体类及其他信息转换成聚合返回给上层，用于查
     * @param backlogitem 实体类
     * @param memberIds 项目成员id列表
     * @return {@link ProjectAggregation}
     */
    private BacklogItemAggregation entityToAggregate(Backlogitem backlogitem, List<String> memberIds, List<String> sprintIds, List<String> releaseIds, List<String> backlogitemIds){
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(backlogitem.getId(),backlogitem.getPriority(),backlogitem.getStartTime(),backlogitem.getEndTime(),backlogitem.getSource(),backlogitem.getType(),backlogitem.getDescription(),backlogitem.getProjectId(),backlogitem.getManagerId(),backlogitem.getScheduleId(),memberIds, backlogitem.getTopic(),sprintIds,releaseIds,backlogitem.getStatus(),backlogitem.getOrganizationId(),backlogitemIds);
        return backlogItemAggregation;
    }

    /**
     * 将聚合中的信息保存到数据库当中，用于增删改
     * @param backlogItemAggregation 项目聚合
     * @return int
     */
    private int saveAggregate(BacklogItemAggregation backlogItemAggregation){
        try{
            // 对实体集进行操作
            Backlogitem backlogitem = new Backlogitem();
            BeanUtils.copyProperties(backlogItemAggregation, backlogitem);
            int result = backlogitemMapper.updateByPrimaryKey(backlogitem);

            // 插入与成员的关系
            associateWithMember(backlogitem.getId(),backlogItemAggregation.getMemberIds());

            // 插入与迭代的联系
            associateWithSprint(backlogitem.getId(),backlogItemAggregation.getSprintIds());

            // 插入与发布的联系
            associateWithRelease(backlogitem.getId(),backlogItemAggregation.getReleaseIds());

            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public BacklogItemAggregation selectById(String id){
        try{
            // 查询实体集中的信息
            Backlogitem backlogitem = backlogitemMapper.selectByPrimaryKey(id);

            // 查询成员
            BacklogitemMemberExample example = new BacklogitemMemberExample();
            example.createCriteria().andBacklogitemIdEqualTo(id);
            List<BacklogitemMemberKey> backlogitemMemberKeys = backlogitemMemberMapper.selectByExample(example);
            List<String> memberIds = backlogitemMemberKeys.stream().map(BacklogitemMemberKey::getAccountId).collect(Collectors.toList());

            // 查询迭代
            BacklogitemSprintExample example1 = new BacklogitemSprintExample();
            example1.createCriteria().andBacklogitemIdEqualTo(id);
            List<BacklogitemSprintKey> backlogitemSprints = backlogitemSprintMapper.selectByExample(example1);
            List<String> sprintIds = backlogitemSprints.stream().map(BacklogitemSprintKey::getSprintId).collect(Collectors.toList());

            // 查询发布
            BacklogitemReleaseExample example2 = new BacklogitemReleaseExample();
            example2.createCriteria().andBacklogitemIdEqualTo(id);
            List<BacklogitemReleaseKey> backlogitemReleases = backlogitemReleaseMapper.selectByExample(example2);
            List<String> releaseIds = backlogitemReleases.stream().map(BacklogitemReleaseKey::getReleaseId).collect(Collectors.toList());

            // 查询关联的工作项
            BacklogitemBacklogitemExample example3 = new BacklogitemBacklogitemExample();
            example3.createCriteria().andBacklogitemid1EqualTo(id);
            List<BacklogitemBacklogitem> backlogitemBacklogitems = backlogitemBacklogitemMapper.selectByExample(example3);
            List<String> backlogitemIds = backlogitemBacklogitems.stream().map(BacklogitemBacklogitem::getBacklogitemid2).collect(Collectors.toList());

            BacklogitemBacklogitemExample example4 = new BacklogitemBacklogitemExample();
            example4.createCriteria().andBacklogitemid2EqualTo(id);
            List<BacklogitemBacklogitem> backlogitemBacklogitems2 = backlogitemBacklogitemMapper.selectByExample(example4);
            List<String> backlogitemIds2 = backlogitemBacklogitems2.stream().map(BacklogitemBacklogitem::getBacklogitemid1).collect(Collectors.toList());
            backlogitemIds.addAll(backlogitemIds2);

            // 将实体集和联系集中的信息转换成聚合返回给上层
            BacklogItemAggregation backlogItemAggregation = entityToAggregate(backlogitem, memberIds, sprintIds, releaseIds, backlogitemIds);
            return backlogItemAggregation;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }



    public List<BacklogItemAggregation> selectAll(){
        List<Backlogitem> backlogitems = backlogitemMapper.selectByExample(null);
        List<BacklogItemAggregation> backlogItemAggregations = new ArrayList<>();
        for(Backlogitem backlogitem:backlogitems){
            BacklogItemAggregation backlogItemAggregation = selectById(backlogitem.getId());
            backlogItemAggregations.add(backlogItemAggregation);
        }
        return backlogItemAggregations;
    }

    public int insert(BacklogItemAggregation backlogItemAggregation){
        try {
            Backlogitem backlogitem = new Backlogitem();
            BeanUtils.copyProperties(backlogItemAggregation, backlogitem);

            // List<String> names = backlogitemMapper.selectByExample(null).stream().map(Backlogitem::getId).collect(Collectors.toList());

            // 使用项目名称+个数生成工作项id
            if(backlogitem.getId()==null){
                System.out.println(backlogitem.getProjectId());
                String projectTopic = projectMapper.selectByPrimaryKey(backlogitem.getProjectId()).getTopic();
                BacklogitemExample example = new BacklogitemExample();
                example.createCriteria().andProjectIdEqualTo(backlogitem.getProjectId());
                long count = backlogitemMapper.countByExample(example)+1;
                System.out.println(projectTopic + " " + count);
                backlogitem.setId(projectTopic + "-" + count);
                backlogitem.setProjectTopic(projectTopic);
                backlogItemAggregation.setId(projectTopic + "-" + count);
                backlogItemAggregation.setProjectTopic(projectTopic);
            }

            // 新建schedule
            ScheduleAggregation scheduleAggregation = scheduleFactory.createSchedule(UUID.randomUUID().toString(),0,0,0,0);
            scheduleRepository.insert(scheduleAggregation);


            System.out.println(backlogitem);
            int result = backlogitemMapper.insert(backlogitem);
            saveAggregate(backlogItemAggregation);
            return result;

        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(BacklogItemAggregation backlogItemAggregation){
        try{
            String projectTopic = projectMapper.selectByPrimaryKey(backlogItemAggregation.getProjectId()).getTopic();
            backlogItemAggregation.setProjectTopic(projectTopic);
            return saveAggregate(backlogItemAggregation);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(String id){
        try{
            System.out.println("Repo Delete backlogitem: " + id);
            return backlogitemMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithBacklogItem(String backlogItemId1,String backlogItemId2) throws InsertFailureException {
        BacklogitemBacklogitemExample example = new BacklogitemBacklogitemExample();
        example.createCriteria().andBacklogitemid1EqualTo(backlogItemId1).andBacklogitemid2EqualTo(backlogItemId2);
        List<BacklogitemBacklogitem> backlogitemBacklogitems = backlogitemBacklogitemMapper.selectByExample(example);
        if(backlogitemBacklogitems.size()!=0)
            throw new InsertFailureException("已经存在关联关系");
        example.createCriteria().andBacklogitemid1EqualTo(backlogItemId2).andBacklogitemid2EqualTo(backlogItemId1);
        backlogitemBacklogitems = backlogitemBacklogitemMapper.selectByExample(example);
        if(backlogitemBacklogitems.size()!=0)
            throw new InsertFailureException("已经存在关联关系");

        BacklogitemBacklogitem backlogitemBacklogitem = new BacklogitemBacklogitem();
        backlogitemBacklogitem.setBacklogitemid1(backlogItemId1);
        backlogitemBacklogitem.setBacklogitemid2(backlogItemId2);
        backlogitemBacklogitemMapper.insert(backlogitemBacklogitem);
        return 0;

    }
    public int associateWithProductRequirement(String backlogItemId,String productRequirementId){
        try{
            RequirementBacklogitemKey key = new RequirementBacklogitemKey();
            key.setBacklogitemId(backlogItemId);
            key.setRequirementContentId(productRequirementId);
            requirementBacklogitemMapper.insert(key);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithMember(String backlogItemId,List<String> memberId){
        try{
            if(memberId==null)
                return 0;
            // 首先删除联系集中project对应的所有记录
            BacklogitemMemberExample example = new BacklogitemMemberExample();
            example.createCriteria().andBacklogitemIdEqualTo(backlogItemId);
            int rows = backlogitemMemberMapper.deleteByExample(example);
            System.out.println("Deleted member rows: " + rows);

            // 再添加当前所有的成员到联系集当中
            for(String id:memberId){
                BacklogitemMemberKey backlogitemMember = new BacklogitemMemberKey();
                backlogitemMember.setBacklogitemId(backlogItemId);
                backlogitemMember.setAccountId(id);
                backlogitemMemberMapper.insert(backlogitemMember);
            }

            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithSprint(String backlogItemId,List<String> sprintIds){
        try{
            if(sprintIds == null)
                return 0;
            // 首先删除联系集中project对应的所有记录
            BacklogitemSprintExample example = new BacklogitemSprintExample();
            example.createCriteria().andBacklogitemIdEqualTo(backlogItemId);
            int rows = backlogitemSprintMapper.deleteByExample(example);
            System.out.println("Deleted sprint rows: " + rows);

            // 再添加当前所有的成员到联系集当中
            for(String id:sprintIds){
                BacklogitemSprintKey backlogitemSprint = new BacklogitemSprintKey();
                backlogitemSprint.setBacklogitemId(backlogItemId);
                backlogitemSprint.setSprintId(id);
                backlogitemSprintMapper.insert(backlogitemSprint);
            }

            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithRelease(String backlogItemId,List<String> releaseIds){
        try{
            if(releaseIds == null)
                return 0;
            // 首先删除联系集中project对应的所有记录
            BacklogitemReleaseExample example = new BacklogitemReleaseExample();
            example.createCriteria().andBacklogitemIdEqualTo(backlogItemId);
            int rows = backlogitemReleaseMapper.deleteByExample(example);
            System.out.println("Deleted release rows: " + rows);

            // 再添加当前所有的成员到联系集当中
            for(String id:releaseIds){
                BacklogitemReleaseKey backlogitemRelease = new BacklogitemReleaseKey();
                backlogitemRelease.setBacklogitemId(backlogItemId);
                backlogitemRelease.setReleaseId(id);
                backlogitemReleaseMapper.insert(backlogitemRelease);
            }

            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertSrint(String backlogItemId,String sprintId){
        try{
            BacklogitemSprintKey backlogitemSprint = new BacklogitemSprintKey();
            backlogitemSprint.setBacklogitemId(backlogItemId);
            backlogitemSprint.setSprintId(sprintId);
            backlogitemSprintMapper.insert(backlogitemSprint);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertRelease(String backlogItemId,String releaseId){
        try{
            BacklogitemReleaseKey backlogitemRelease = new BacklogitemReleaseKey();
            backlogitemRelease.setBacklogitemId(backlogItemId);
            backlogitemRelease.setReleaseId(releaseId);
            backlogitemReleaseMapper.insert(backlogitemRelease);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int cancelAssociate(String backlogItemId1,String backlogItemId2){
        try{
            BacklogitemBacklogitemExample example = new BacklogitemBacklogitemExample();
            example.createCriteria().andBacklogitemid1EqualTo(backlogItemId1).andBacklogitemid2EqualTo(backlogItemId2);
            backlogitemBacklogitemMapper.deleteByExample(example);
            example.createCriteria().andBacklogitemid1EqualTo(backlogItemId2).andBacklogitemid2EqualTo(backlogItemId1);
            backlogitemBacklogitemMapper.deleteByExample(example);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

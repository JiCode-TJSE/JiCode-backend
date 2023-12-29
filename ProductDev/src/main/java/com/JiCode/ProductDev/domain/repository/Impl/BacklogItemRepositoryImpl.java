package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.*;
import com.JiCode.ProductDev.domain.factory.BacklogItemFactory;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
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
    @Autowired
    BacklogItemFactory backlogItemFactory;
    @Autowired
    BacklogitemMapper backlogitemMapper;
    @Autowired
    BacklogitemMemberMapper backlogitemMemberMapper;

    @Autowired
    BacklogitemBacklogitemMapper backlogitemBacklogitemMapper;

    @Autowired
    RequirementBacklogitemMapper requirementBacklogitemMapper;

    @Autowired
    ProjectMapper projectMapper;

    /**
     * 将实体类及其他信息转换成聚合返回给上层，用于查
     * @param backlogitem 实体类
     * @param memberIds 项目成员id列表
     * @return {@link ProjectAggregation}
     */
    private BacklogItemAggregation entityToAggregate(Backlogitem backlogitem, List<String> memberIds){
        BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(backlogitem.getId(),backlogitem.getPriority(),backlogitem.getStartTime(),backlogitem.getEndTime(),backlogitem.getSource(),backlogitem.getType(),backlogitem.getDescription(),backlogitem.getProjectId(),backlogitem.getManagerId(),backlogitem.getScheduleId(),memberIds, backlogitem.getTopic());
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

            // 对联系集进行操作
            // 首先删除联系集中project对应的所有记录
            BacklogitemMemberExample example = new BacklogitemMemberExample();
            example.createCriteria().andBacklogitemIdEqualTo(backlogitem.getId());
            int rows = backlogitemMemberMapper.deleteByExample(example);
            System.out.println("Deleted rows: " + rows);


            // 再添加当前所有的成员到联系集当中
            List<String> memberIds = backlogItemAggregation.getMembers();
            BacklogitemMemberKey backlogitemMember = new BacklogitemMemberKey();
            for(String memberId:memberIds){
                backlogitemMember.setBacklogitemId(backlogitem.getId());
                backlogitemMember.setAccountId(memberId);
                backlogitemMemberMapper.insert(backlogitemMember);
            }
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

            // 查询联系集中的信息
            BacklogitemMemberExample example = new BacklogitemMemberExample();
            example.createCriteria().andBacklogitemIdEqualTo(id);
            List<BacklogitemMemberKey> backlogitemMemberKeys = backlogitemMemberMapper.selectByExample(example);
            List<String> memberIds = backlogitemMemberKeys.stream().map(BacklogitemMemberKey::getAccountId).collect(Collectors.toList());

            // 将实体集和联系集中的信息转换成聚合返回给上层
            BacklogItemAggregation backlogItemAggregation = entityToAggregate(backlogitem, memberIds);
            return backlogItemAggregation;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public PageInfo<BacklogItemAggregation> getPage(int pageNum, int pageSize){
        try{
            // 分页查询
            PageHelper.startPage(pageNum, pageSize);
            Page<Backlogitem> backlogitems = backlogitemMapper.selectByPaging(null);
            //System.out.println(backlogitems);
            List<BacklogItemAggregation> backlogItemAggregations = new ArrayList<>();
            for (Backlogitem backlogitem : backlogitems) {
                System.out.println(backlogitem.getScheduleId());
                // 获取成员列表
                BacklogitemMemberExample example = new BacklogitemMemberExample();
                example.createCriteria().andBacklogitemIdEqualTo(backlogitem.getId());
                List<BacklogitemMemberKey> backlogitemMemberKeys = backlogitemMemberMapper.selectByExample(example);
                List<String> memberIds = backlogitemMemberKeys.stream().map(BacklogitemMemberKey::getAccountId).collect(Collectors.toList());

                // 工厂模式创建ProjectAggregation
                BacklogItemAggregation backlogItemAggregation = backlogItemFactory.createBacklogItem(backlogitem.getId(),backlogitem.getPriority(),backlogitem.getStartTime(),backlogitem.getEndTime(),backlogitem.getSource(),backlogitem.getType(),backlogitem.getDescription(),backlogitem.getProjectId(),backlogitem.getManagerId(),backlogitem.getScheduleId(), memberIds, backlogitem.getTopic()); // use builder to create ProjectAggregation
                backlogItemAggregations.add(backlogItemAggregation);
            }
            return new PageInfo<>(backlogItemAggregations);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int insert(BacklogItemAggregation backlogItemAggregation){
        try {
            Backlogitem backlogitem = new Backlogitem();
            BeanUtils.copyProperties(backlogItemAggregation, backlogitem);

            // 使用UUID生成ID
            if(backlogitem.getId()==null){
                String projectTopic = projectMapper.selectByPrimaryKey(backlogitem.getProjectId()).getTopic();
                BacklogitemExample example = new BacklogitemExample();
                example.createCriteria().andTopicEqualTo(projectTopic);
                String count = String.valueOf(backlogitemMapper.countByExample(example))+1;
                System.out.println(projectTopic + " " + count);
                backlogitem.setId(projectTopic + "-" + count);
            }
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
            return saveAggregate(backlogItemAggregation);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(String id){
        try{
            return backlogitemMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithBacklogItem(String backlogItemId1,String backlogItemId2){
        try{
            BacklogitemBacklogitem backlogitemBacklogitem = new BacklogitemBacklogitem();
            backlogitemBacklogitem.setBacklogitemid1(backlogItemId1);
            backlogitemBacklogitem.setBacklogitemid2(backlogItemId2);
            backlogitemBacklogitemMapper.insert(backlogitemBacklogitem);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
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
}

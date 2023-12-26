package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.SprintMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.SprintMemberMapper;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service("SprintRepository")
public class SprintRepositoryImpl implements SprintRepository {
    @Autowired
    SprintMapper sprintMapper;

    @Autowired
    SprintMemberMapper sprintMemberMapper;


    /**
     * @param sprint
     * @param memberIds
     * @return {@link SprintAggregation}
     */
    private SprintAggregation entityToAggregate(Sprint sprint, List<String> memberIds){
        SprintAggregation sprintAggregation = SprintAggregation.createSprint(sprint.getId(), sprint.getStartTime(),sprint.getEndTime(),sprint.getGoal(),sprint.getType(),sprint.getProjectId(),sprint.getManagerId(),sprint.getReleaseId(),memberIds);
        return sprintAggregation;
    }

    /**
     * @param sprintAggregation
     * @return int
     */
    private int saveAggregate(SprintAggregation sprintAggregation){
        try{
            // 对实体集进行操作
            Sprint sprint = new Sprint();
            BeanUtils.copyProperties(sprintAggregation, sprint);
            int result = sprintMapper.updateByPrimaryKey(sprint);

            // 对联系集进行操作
            // 首先删除联系集中sprint对应的所有记录
            // 创建一个新的SprintMemberExample对象
            SprintMemberExample example = new SprintMemberExample();
            SprintMemberExample.Criteria criteria = example.createCriteria();
            criteria.andSprintIdEqualTo(sprint.getId());
            int rows = sprintMemberMapper.deleteByExample(example);
            System.out.println("Deleted rows: " + rows);

            // 再添加当前所有的成员进入
            List<String> memberIds = sprintAggregation.getMemberIds();
            SprintMember sprintMember = new SprintMember();
            for(String memberId:memberIds){
                sprintMember.setSprintId(sprint.getId());
                sprintMember.setAccountId(memberId);
                sprintMemberMapper.insert(sprintMember);
            }
            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }


    public SprintAggregation selectById(String id){
        try{
            Sprint sprint = sprintMapper.selectByPrimaryKey(id);
            // 获取迭代成员id列表
            SprintMemberExample example = new SprintMemberExample();
            example.createCriteria().andSprintIdEqualTo("1");
            List<SprintMember> sprintMembers = sprintMemberMapper.selectByExample(example);
            List<String> memberIds = new ArrayList<>();
            for (SprintMember sprintMember : sprintMembers) {
                memberIds.add(sprintMember.getAccountId());
            }

            //工厂方法构造聚合
            SprintAggregation sprintAggregation = entityToAggregate(sprint, memberIds);
            return sprintAggregation;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public PageInfo<SprintAggregation> getPage(int pageNum, int pageSize){
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<Sprint> sprints = sprintMapper.selectByPaging(null);
            List<SprintAggregation> sprintAggregations = new ArrayList<>();
            for (Sprint sprint : sprints) {
                // 获取迭代成员id列表
                SprintMemberExample example = new SprintMemberExample();
                example.createCriteria().andSprintIdEqualTo(sprint.getId());
                List<SprintMember> sprintMembers = sprintMemberMapper.selectByExample(example);
                List<String> memberIds = new ArrayList<>();
                for (SprintMember sprintMember : sprintMembers) {
                    memberIds.add(sprintMember.getAccountId());
                }

                //工厂方法构造聚合
                SprintAggregation sprintAggregation = entityToAggregate(sprint, memberIds);
                sprintAggregations.add(sprintAggregation);
            }
            return new PageInfo<>(sprintAggregations);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public int insert(SprintAggregation sprintAggregation){
        try {
            Sprint sprint = new Sprint();
            BeanUtils.copyProperties(sprintAggregation, sprint);
            if(sprint.getId() == null) {
                sprint.setId(UUID.randomUUID().toString());
            }
            return sprintMapper.insert(sprint);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(SprintAggregation sprintAggregation){
        try{
            return saveAggregate(sprintAggregation);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(String id){
        try{
            return sprintMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

}

package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.BacklogitemSprintMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.SprintMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.SprintMemberMapper;
import com.JiCode.ProductDev.domain.factory.SprintFactory;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.exceptions.sprint.*;
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
 * 仓储实现，注入两个部分： mapper,factory
 * @author Laurent Wu
 * @date 2023/12/26
 */
@Service("SprintRepository")
public class SprintRepositoryImpl implements SprintRepository {
    @Autowired
    SprintMapper sprintMapper;

    @Autowired
    SprintMemberMapper sprintMemberMapper;

    @Autowired
    BacklogitemSprintMapper backlogitemSprintMapper;

    @Autowired
    SprintFactory SprintFactory;


    /**
     * @param sprint
     * @param memberIds
     * @return {@link SprintAggregation}
     */
    private SprintAggregation entityToAggregate(Sprint sprint, List<String> memberIds, List<String> backlogItemIds){
        SprintAggregation sprintAggregation = SprintFactory.createSprint(sprint.getId(), sprint.getStartTime(),sprint.getEndTime(),sprint.getGoal(),sprint.getType(),sprint.getProjectId(),sprint.getManagerId(),sprint.getReleaseId(),memberIds, sprint.getTopic(),backlogItemIds, sprint.getOrganizationId());
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
            example.createCriteria().andSprintIdEqualTo(id);
            List<SprintMember> sprintMembers = sprintMemberMapper.selectByExample(example);
            List<String> memberIds = new ArrayList<>();
            for (SprintMember sprintMember : sprintMembers) {
                memberIds.add(sprintMember.getAccountId());
            }

            // 获取迭代当中的工作项
            BacklogitemSprintExample example1 = new BacklogitemSprintExample();
            example1.createCriteria().andSprintIdEqualTo(id);
            List<BacklogitemSprintKey> backlogitemSprints = backlogitemSprintMapper.selectByExample(example1);
            List<String> backlogItemIds = new ArrayList<>();
            for (BacklogitemSprintKey backlogitemSprint : backlogitemSprints) {
                backlogItemIds.add(backlogitemSprint.getBacklogitemId());
            }

            //工厂方法构造聚合
            SprintAggregation sprintAggregation = entityToAggregate(sprint, memberIds,backlogItemIds);
            return sprintAggregation;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<SprintAggregation> selectAll(){
        List<Sprint> sprints = sprintMapper.selectByExample(null);
        List<SprintAggregation> sprintAggregations = new ArrayList<>();
        for(Sprint sprint:sprints){
            SprintAggregation sprintAggregation = selectById(sprint.getId());
            sprintAggregations.add(sprintAggregation);
        }
        return sprintAggregations;
    }

    public PageInfo<SprintAggregation> getPage(int pageNum, int pageSize){
        try{
            // 根据条件进行分页查询
            PageHelper.startPage(pageNum, pageSize);
            Page<Sprint> sprints = sprintMapper.selectByPaging(null);

            List<SprintAggregation> sprintAggregations = new ArrayList<>();
            for (Sprint sprint : sprints) {
                System.out.println(sprint.getType());
                // 获取迭代成员id列表
                SprintMemberExample example = new SprintMemberExample();
                example.createCriteria().andSprintIdEqualTo(sprint.getId());
                List<SprintMember> sprintMembers = sprintMemberMapper.selectByExample(example);
                List<String> memberIds = new ArrayList<>();
                for (SprintMember sprintMember : sprintMembers) {
                    memberIds.add(sprintMember.getAccountId());
                }

                // 获取迭代当中的工作项
                BacklogitemSprintExample example1 = new BacklogitemSprintExample();
                example1.createCriteria().andSprintIdEqualTo(sprint.getId());
                List<BacklogitemSprintKey> backlogitemSprints = backlogitemSprintMapper.selectByExample(example1);
                List<String> backlogItemIds = new ArrayList<>();
                for (BacklogitemSprintKey backlogitemSprint : backlogitemSprints) {
                    backlogItemIds.add(backlogitemSprint.getBacklogitemId());
                }

                //工厂方法构造聚合
                SprintAggregation sprintAggregation = entityToAggregate(sprint, memberIds,backlogItemIds);
                sprintAggregations.add(sprintAggregation);
            }
            return new PageInfo<>(sprintAggregations);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public int insert(SprintAggregation sprintAggregation) throws InsertFailureException {
        try {
            Sprint sprint = new Sprint();
            BeanUtils.copyProperties(sprintAggregation, sprint);
            System.out.println("sprint:"+sprint.getStartTime());
            if(sprint.getId() == null) {
                sprint.setId(UUID.randomUUID().toString());
            }
            return sprintMapper.insert(sprint);
        }catch (Exception e){
            System.out.println(e);
            throw new InsertFailureException(e.getMessage());
        }
    }

    public int updateById(SprintAggregation sprintAggregation) throws UpdateFaliureException {
        try{
            // 保存聚合
            int result = saveAggregate(sprintAggregation);
            if(result==1){
                // 保存迭代与工作项的联系
                for(String backlogItemId:sprintAggregation.getBacklogItemIds()){
                    associateWithBacklogItem(sprintAggregation.getId(),backlogItemId);
                }
            }
            return result;
        }catch (Exception e){
            System.out.println(e);
            throw new UpdateFaliureException(e.getMessage());
        }
    }

    public int deleteById(String id) throws DeleteFailureException {
        try{
            return sprintMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new DeleteFailureException(e.getMessage());
        }
    }

    /**
     * 提供修改release_sprint联系集的方法
     * @param sprintId
     * @param releaseId
     * @return int
     */
    public int setRelease(String sprintId, String releaseId) throws SetReleaseException {
        try{
            Sprint sprint = sprintMapper.selectByPrimaryKey(sprintId);
            if(sprint == null)
                throw new SprintNotFoundException("Can not find such sprint.");
            sprint.setReleaseId(releaseId);
            int result = sprintMapper.updateByPrimaryKey(sprint);
            return result;
        }catch(Exception e){
            throw new SetReleaseException(e.getMessage());
        }
    }

    public int associateWithRelease(String sprintId, String releaseId){
        try{
            Sprint sprint = sprintMapper.selectByPrimaryKey(sprintId);
            if(sprint == null)
                throw new SprintNotFoundException("Can not find such sprint.");
            sprint.setReleaseId(releaseId);
            int result = sprintMapper.updateByPrimaryKey(sprint);
            return result;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithBacklogItem(String sprintId, String backlogItemId){
        try{
            BacklogitemSprintKey key = new BacklogitemSprintKey();
            key.setBacklogitemId(backlogItemId);
            key.setSprintId(sprintId);
            int result = backlogitemSprintMapper.insert(key);
            return result;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

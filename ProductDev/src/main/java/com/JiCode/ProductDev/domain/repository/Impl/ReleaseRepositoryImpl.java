package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.BacklogitemReleaseMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ReleaseMapper;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.ReleaseMemberMapper;
import com.JiCode.ProductDev.domain.factory.ReleaseFactory;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ReleaseRepository")
public class ReleaseRepositoryImpl implements ReleaseRepository{
    @Autowired
    ReleaseMapper releaseMapper;
    @Autowired
    ReleaseFactory releaseFactory;

    @Autowired
    ReleaseMemberMapper releaseMemberMapper;

    @Autowired
    BacklogitemReleaseMapper backlogitemReleaseMapper;

    private ReleaseAggregation entityToAggregate(Release release, List<String> memberIds, List<String> backlogItemIds){
        ReleaseAggregation releaseAggregation = releaseFactory.createRelease(release.getId(), release.getStartTime(), release.getEndTime(), release.getType(), release.getProjectId(), release.getManagerId(), memberIds,release.getTopic(), release.getStage(), backlogItemIds);
        return releaseAggregation;
    }

    private int saveAggregate(ReleaseAggregation releaseAggregation){
        try{
            Release release = new Release();
            BeanUtils.copyProperties(releaseAggregation, release);
            int result = releaseMapper.updateByPrimaryKey(release);

            // 删除原有成员
            ReleaseMemberExample example = new ReleaseMemberExample();
            ReleaseMemberExample.Criteria criteria = example.createCriteria();
            criteria.andReleaseIdEqualTo(release.getId());
            int rows = releaseMemberMapper.deleteByExample(example);
            System.out.println("Deleted rows: " + rows);

            // 将现有成员全部插入
            List<String> memberIds = releaseAggregation.getMemberIds();
            ReleaseMember releaseMember = new ReleaseMember();
            releaseMember.setReleaseId(release.getId());
            for(String memberId:memberIds){
                releaseMember.setAccountId(memberId);
                releaseMemberMapper.insert(releaseMember);
            }
            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public ReleaseAggregation selectById(String id){
        try{
            Release release = releaseMapper.selectByPrimaryKey(id);
            // 获取所有成员
            ReleaseMemberExample example = new ReleaseMemberExample();
            example.createCriteria().andReleaseIdEqualTo(id);
            List<ReleaseMember> releaseMembers = releaseMemberMapper.selectByExample(example);
            List<String>memberIds = releaseMembers.stream().map(ReleaseMember::getAccountId).collect(Collectors.toList());

            // 获取所有工作项
            BacklogitemReleaseExample example2 = new BacklogitemReleaseExample();
            example2.createCriteria().andReleaseIdEqualTo(id);
            List<BacklogitemReleaseKey> backlogitemReleaseKeys = backlogitemReleaseMapper.selectByExample(example2);
            List<String> backlogItemIds = backlogitemReleaseKeys.stream().map(BacklogitemReleaseKey::getBacklogitemId).collect(Collectors.toList());


            ReleaseAggregation releaseAggregation = entityToAggregate(release, memberIds, backlogItemIds);
            return releaseAggregation;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public PageInfo<ReleaseAggregation> getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Release> releases = releaseMapper.selectByPaging(null);

        List<ReleaseAggregation> releaseAggregations = new ArrayList<>();
        for (Release release : releases) {
            ReleaseMemberExample example = new ReleaseMemberExample();
            example.createCriteria().andReleaseIdEqualTo(release.getId());
            List<ReleaseMember> releaseMember = releaseMemberMapper.selectByExample(example);
            List<String> memberIds = releaseMember.stream().map(ReleaseMember::getAccountId).collect(Collectors.toList());

            // 获取所有工作项
            BacklogitemReleaseExample example2 = new BacklogitemReleaseExample();
            example2.createCriteria().andReleaseIdEqualTo(release.getId());
            List<BacklogitemReleaseKey> backlogitemReleaseKeys = backlogitemReleaseMapper.selectByExample(example2);
            List<String> backlogItemIds = backlogitemReleaseKeys.stream().map(BacklogitemReleaseKey::getBacklogitemId).collect(Collectors.toList());

            ReleaseAggregation releaseAggregation = entityToAggregate(release, memberIds,backlogItemIds);
            releaseAggregations.add(releaseAggregation);
        }

        return new PageInfo<>(releaseAggregations);
    }

    public int insert(ReleaseAggregation releaseAggregation){
        try{
            Release release = new Release();
            BeanUtils.copyProperties(releaseAggregation, release);
            int result = releaseMapper.insert(release);

            List<String> memberIds = releaseAggregation.getMemberIds();
            ReleaseMember releaseMember = new ReleaseMember();
            releaseMember.setReleaseId(release.getId());
            for(String memberId:memberIds){
                releaseMember.setAccountId(memberId);
                releaseMemberMapper.insert(releaseMember);
            }

            associateWithBacklogItem(release.getId(), releaseAggregation.getBacklogItemIds());
            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }


    public int update(ReleaseAggregation releaseAggregation){
        int result = saveAggregate(releaseAggregation);
        associateWithBacklogItem(releaseAggregation.getId(), releaseAggregation.getBacklogItemIds());
        return result;
    }

    public int deleteById(String id){
        try{
            ReleaseMemberExample example = new ReleaseMemberExample();
            example.createCriteria().andReleaseIdEqualTo(id);
            int rows = releaseMemberMapper.deleteByExample(example);
            System.out.println("Deleted rows: " + rows);
            return releaseMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int associateWithBacklogItem(String releaseId, List<String> backlogItemIds){
        try{
            // 首先删除联系集中project对应的所有记录
            BacklogitemReleaseExample example = new BacklogitemReleaseExample();
            example.createCriteria().andBacklogitemIdEqualTo(releaseId);
            int rows = backlogitemReleaseMapper.deleteByExample(example);
            System.out.println("Deleted release rows: " + rows);

            // 再添加当前所有的成员到联系集当中
            for(String backlogItemId:backlogItemIds){
                BacklogitemReleaseKey backlogitemRelease = new BacklogitemReleaseKey();
                backlogitemRelease.setBacklogitemId(backlogItemId);
                backlogitemRelease.setReleaseId(releaseId);
                backlogitemReleaseMapper.insert(backlogitemRelease);
            }
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertBacklogItem(String releaseId,String backlogItemId){
        try{
            BacklogitemReleaseKey backlogitemRelease = new BacklogitemReleaseKey();
            backlogitemRelease.setBacklogitemId(backlogItemId);
            backlogitemRelease.setReleaseId(releaseId);
            backlogitemReleaseMapper.insert(backlogitemRelease);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

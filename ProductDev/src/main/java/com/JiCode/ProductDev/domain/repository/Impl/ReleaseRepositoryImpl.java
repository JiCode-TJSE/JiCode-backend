package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.*;
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

    private ReleaseAggregation entityToAggregate(Release release, List<String> memberIds){
        ReleaseAggregation releaseAggregation = releaseFactory.createRelease(release.getId(), release.getStartTime(), release.getEndTime(), release.getType(), release.getProjectId(), release.getManagerId(), memberIds);
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
            System.out.println("select");
            Release release = releaseMapper.selectByPrimaryKey(id);
            System.out.println("select finished");
            ReleaseMemberExample example = new ReleaseMemberExample();
            example.createCriteria().andReleaseIdEqualTo(id);
            List<ReleaseMember> releaseMembers = releaseMemberMapper.selectByExample(example);
            List<String>memberIds = releaseMembers.stream().map(ReleaseMember::getAccountId).collect(Collectors.toList());
            ReleaseAggregation releaseAggregation = entityToAggregate(release, memberIds);
            return releaseAggregation;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

//    public PageInfo<ReleaseAggregation> getPage(int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        Page<Release> releases = releaseMapper.selectByPaging(null);
//
//        List<ReleaseAggregation> releaseAggregations = new ArrayList<>();
//        for (Release release : releases) {
//            ReleaseMemberExample example = new ReleaseMemberExample();
//            example.createCriteria().andReleaseIdEqualTo(release.getId());
//            List<ReleaseMemberKey> releaseMemberKeys = releaseMemberMapper.selectByExample(example);
//            List<String> memberIds = releaseMemberKeys.stream().map(ReleaseMemberKey::getAccountId).collect(Collectors.toList());
//            ReleaseAggregation releaseAggregation = entityToAggregate(release, memberIds);
//            releaseAggregations.add(releaseAggregation);
//        }
//
//        return new PageInfo<>(releaseAggregations);
//    }

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
            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int update(ReleaseAggregation releaseAggregation){
        return saveAggregate(releaseAggregation);
    }
}

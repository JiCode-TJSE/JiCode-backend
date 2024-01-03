package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Stage;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.StageExample;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.StageMapper;
import com.JiCode.ProductDev.common.ReleaseStageEnum;
import com.JiCode.ProductDev.domain.factory.ReleaseFactory;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.model.value.ReleaseStage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReleaseFactoryImpl implements ReleaseFactory {
    @Autowired
    StageMapper stageMapper;
    public ReleaseAggregation createRelease(String id, Date startTime, Date endTime, String type, String projectid, String managerId, List<String> memberIds, String topic, String stageId, List<String> backlogItemIds, String organizationId){

        ReleaseAggregation releaseAggregation = new ReleaseAggregation();
        if(id == null){
            id = UUID.randomUUID().toString();
        }
        releaseAggregation.setId(id);
        releaseAggregation.setStartTime(startTime);
        releaseAggregation.setEndTime(endTime);
        releaseAggregation.setManagerId(managerId);
        releaseAggregation.setMemberIds(memberIds);
        releaseAggregation.setTopic(topic);
        releaseAggregation.setStageId(stageId);
        releaseAggregation.setBacklogItemIds(backlogItemIds);
        releaseAggregation.setOrganizationId(organizationId);
        releaseAggregation.setProjectId(projectid);

        // 获取发布阶段
        System.out.println("Factory:");
        StageExample stageExample = new StageExample();
        stageExample.createCriteria().andReleaseIdEqualTo(id);
        List<Stage> stages = stageMapper.selectByExample(stageExample);
        for(Stage stage:stages){
            System.out.println(stage.getStage());
        }
        List<ReleaseStage> releaseStages = new ArrayList<>();
        for(Stage stage:stages){
            ReleaseStage releaseStage = new ReleaseStage();
            releaseStage.setStage(ReleaseStageEnum.valueOf(stage.getStage()));
            releaseStage.setDate(stage.getTime());
            releaseStage.setId(stage.getId());
            releaseStages.add(releaseStage);
        }

        releaseAggregation.setStages(releaseStages);

        System.out.println(releaseAggregation);
        return releaseAggregation;
    }
}

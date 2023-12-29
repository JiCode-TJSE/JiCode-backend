package com.JiCode.ProductDev.domain.factory.impl;

import com.JiCode.ProductDev.domain.factory.ReleaseFactory;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReleaseFactoryImpl implements ReleaseFactory {
    public ReleaseAggregation createRelease(String id, Date startTime, Date endTime, String type, String projectid, String managerId, List<String> memberIds, String topic, String stage){

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
        releaseAggregation.setStage(stage);
        System.out.println(releaseAggregation);
        return releaseAggregation;
    }
}

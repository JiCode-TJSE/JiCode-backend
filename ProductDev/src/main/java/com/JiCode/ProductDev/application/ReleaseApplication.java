package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.application.dto.ReleaseDto;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseApplication {

    @Autowired
    ReleaseRepository releaseRepository;

    @Transactional(readOnly = true)
    public List<ReleaseDto> selectAll(String organizationId){
        List<ReleaseAggregation> releaseAggregations=releaseRepository.selectAll();
        List<ReleaseDto> ans = new ArrayList<>();
        for(ReleaseAggregation releaseAggregation:releaseAggregations){
            if(releaseAggregation.getOrganizationId().equals(organizationId)){
                ReleaseDto releaseDto = new ReleaseDto();
                releaseDto.setId(releaseAggregation.getId());
                releaseDto.setStartTime(releaseAggregation.getStartTime());
                releaseDto.setEndTime(releaseAggregation.getEndTime());
                releaseDto.setType(releaseAggregation.getType());
                releaseDto.setProjectId(releaseAggregation.getProjectId());
                releaseDto.setManagerId(releaseAggregation.getManagerId());
                releaseDto.setMemberIds(releaseAggregation.getMemberIds());
                releaseDto.setTopic(releaseAggregation.getTopic());
                releaseDto.setStageId(releaseAggregation.getStageId());
                releaseDto.setBacklogItemIds(releaseAggregation.getBacklogItemIds());
                releaseDto.setOrganizationId(releaseAggregation.getOrganizationId());
                ans.add(releaseDto);
            }
        }
        return ans;
    }

    @Transactional(readOnly = false)
    public int insert(ReleaseDto releaseDto){
        ReleaseAggregation releaseAggregation = new ReleaseAggregation();
        releaseAggregation.setId(releaseDto.getId());
        releaseAggregation.setStartTime(releaseDto.getStartTime());
        releaseAggregation.setEndTime(releaseDto.getEndTime());
        releaseAggregation.setType(releaseDto.getType());
        releaseAggregation.setProjectId(releaseDto.getProjectId());
        releaseAggregation.setManagerId(releaseDto.getManagerId());
        releaseAggregation.setMemberIds(releaseDto.getMemberIds());
        releaseAggregation.setTopic(releaseDto.getTopic());
        releaseAggregation.setStageId(releaseDto.getStageId());
        releaseAggregation.setBacklogItemIds(releaseDto.getBacklogItemIds());
        releaseAggregation.setOrganizationId(releaseDto.getOrganizationId());
        return releaseRepository.insert(releaseAggregation);
    }
}

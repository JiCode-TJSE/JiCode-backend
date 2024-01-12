package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Release;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Stage;
import com.JiCode.ProductDev.application.dto.ReleaseDto;
import com.JiCode.ProductDev.common.ReleaseStageEnum;
import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.model.value.ReleaseStage;
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
    public List<ReleaseDto> selectAll(String organizationId,String projectId){
        List<ReleaseAggregation> releaseAggregations=releaseRepository.selectAll();
        List<ReleaseDto> ans = new ArrayList<>();
        System.out.println(projectId==null);
        for(ReleaseAggregation releaseAggregation:releaseAggregations){
            if(releaseAggregation.getOrganizationId().equals(organizationId)
                    &&(releaseAggregation.getProjectId().equals(projectId) || projectId==null)){
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
                releaseDto.setStages(releaseAggregation.getStages());

                // 返回发布阶段
                List<ReleaseStage> stages = releaseAggregation.getStages();
                for(ReleaseStage stage:stages){
                    if(stage.getId().equals(releaseAggregation.getStageId())){
                        if(stage.getStage()== ReleaseStageEnum.NotStarted){
                            releaseDto.setStageStatus("未开始");
                        }else if(stage.getStage()== ReleaseStageEnum.InProgress){
                            releaseDto.setStageStatus("进行中");
                        }else{
                            releaseDto.setStageStatus("已发布");
                        }
                    }
                }

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

    @Transactional(readOnly = false)
    public int delete(String releaseId){
        return releaseRepository.deleteById(releaseId);
    }

    public ReleaseDto selectById(String releaseId){
        ReleaseAggregation releaseAggregation=releaseRepository.selectById(releaseId);
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
        releaseDto.setStages(releaseAggregation.getStages());

        // 返回发布阶段
        List<ReleaseStage> stages = releaseAggregation.getStages();
        for(ReleaseStage stage:stages){
            if(stage.getId().equals(releaseAggregation.getStageId())){
                if(stage.getStage()== ReleaseStageEnum.NotStarted){
                    releaseDto.setStageStatus("未开始");
                }else if(stage.getStage()== ReleaseStageEnum.InProgress){
                    releaseDto.setStageStatus("进行中");
                }else{
                    releaseDto.setStageStatus("已发布");
                }
            }
        }

        return releaseDto;
    }

    public int update(ReleaseDto releaseDto){
        System.out.println(releaseDto.getId());
        ReleaseAggregation origin = releaseRepository.selectById(releaseDto.getId());

        ReleaseAggregation releaseAggregation = new ReleaseAggregation();
        releaseAggregation.setId(releaseDto.getId()==null?origin.getId():releaseDto.getId());
        releaseAggregation.setStartTime(releaseDto.getStartTime()==null?origin.getStartTime():releaseDto.getStartTime());
        releaseAggregation.setEndTime(releaseDto.getEndTime()==null?origin.getEndTime():releaseDto.getEndTime());
        releaseAggregation.setType(releaseDto.getType()==null?origin.getType():releaseDto.getType());
        releaseAggregation.setProjectId(releaseDto.getProjectId()==null?origin.getProjectId():releaseDto.getProjectId());
        releaseAggregation.setManagerId(releaseDto.getManagerId()== null?origin.getManagerId():releaseDto.getManagerId());
        releaseAggregation.setMemberIds(releaseDto.getMemberIds()==null?origin.getMemberIds():releaseDto.getMemberIds());
        releaseAggregation.setTopic(releaseDto.getTopic()==null?origin.getTopic():releaseDto.getTopic());
        releaseAggregation.setStageId(releaseDto.getStageId()==null?origin.getStageId():releaseDto.getStageId());
        releaseAggregation.setBacklogItemIds(releaseDto.getBacklogItemIds()==null?origin.getBacklogItemIds():releaseDto.getBacklogItemIds());
        releaseAggregation.setOrganizationId(releaseDto.getOrganizationId()==null?origin.getOrganizationId():releaseDto.getOrganizationId());
        releaseAggregation.setStages(releaseDto.getStages()==null?origin.getStages():releaseDto.getStages());

        return releaseRepository.update(releaseAggregation);
    }
}

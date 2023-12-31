package com.JiCode.ProductDev.domain.service.correlation.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.domain.service.correlation.RelateService;
import com.JiCode.ProductDev.domain.service.correlation.strategy.RelateStrategy;

public class RelateServiceImpl implements RelateService {
    @Autowired
    RelateStrategy relateStrategy;

    // @Autowired
    // BacklogItemRepository backlogItemRepository;

    // @Autowired
    // ReleaseRepository releaseRepository;

    // @Autowired
    // SprintRepository sprintRepository;

    public void relate(RelateBo item1, RelateBo item2){
        relateStrategy.relate(item1, item2);
        // if(item1.type == RelateItemTypeEnum.Backlogitem){
        //     if(item2.type == RelateItemTypeEnum.Backlogitem){
        //         backlogItemRepository.associateWithBacklogItem(item1.id, item2.id);
        //     }
        // }
        // else if(item1.type == RelateItemTypeEnum.Sprint){
        //     if(item2.type == RelateItemTypeEnum.Release){
        //         sprintRepository.associateWithRelease(item1.id, item2.id);
        //     }
        // }
        // else if(item1.type == RelateItemTypeEnum.Release){
        //     if(item2.type == RelateItemTypeEnum.Sprint){
        //         sprintRepository.associateWithRelease(item1.id, item2.id);
        //     }
        // }
    }
}

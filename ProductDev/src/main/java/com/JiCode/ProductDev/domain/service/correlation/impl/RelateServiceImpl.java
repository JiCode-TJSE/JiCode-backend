package com.JiCode.ProductDev.domain.service.correlation.impl;

import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import com.JiCode.ProductDev.domain.repository.SprintRepository;
import com.JiCode.ProductDev.domain.service.correlation.RelateService;
import com.JiCode.ProductDev.domain.service.correlation.strategy.RelateStrategy;

@Service
public class RelateServiceImpl extends RelateStrategy implements RelateService {
    @Autowired
    RelateStrategy relateStrategy;

     @Autowired
     BacklogItemRepository backlogItemRepository;

     @Autowired
     ReleaseRepository releaseRepository;

     @Autowired
     SprintRepository sprintRepository;

    public void relate(RelateBo item1, RelateBo item2) throws InsertFailureException {
//        if(item1.type != RelateItemTypeEnum.Backlogitem){
//            if(item2.type == RelateItemTypeEnum.Backlogitem){
//                RelateBo temp = item1;
//                item1 = item2;
//                item2 = temp;
//            }
//        }
//
//        relateStrategy.relate(item1, item2);

        if(item1.type == RelateItemTypeEnum.Backlogitem){
            if(item2.type == RelateItemTypeEnum.Backlogitem){
                backlogItemRepository.associateWithBacklogItem(item1.id, item2.id);
            }
        }
        else if(item1.type == RelateItemTypeEnum.Sprint){
            if(item2.type == RelateItemTypeEnum.Release){
                sprintRepository.associateWithRelease(item1.id, item2.id);
            }
        }
        else if(item1.type == RelateItemTypeEnum.Release){
            if(item2.type == RelateItemTypeEnum.Sprint){
                sprintRepository.associateWithRelease(item1.id, item2.id);
            }
        }
    }

}

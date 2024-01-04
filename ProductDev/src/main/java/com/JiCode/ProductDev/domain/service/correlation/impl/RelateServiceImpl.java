package com.JiCode.ProductDev.domain.service.correlation.impl;

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

    public void relate(RelateBo item1, RelateBo item2){
        if(item1.type != RelateItemTypeEnum.Backlogitem){
            if(item2.type == RelateItemTypeEnum.Backlogitem){
                RelateBo temp = item1;
                item1 = item2;
                item2 = temp;
            }
        }

        relateStrategy.relate(item1, item2);
    }
}

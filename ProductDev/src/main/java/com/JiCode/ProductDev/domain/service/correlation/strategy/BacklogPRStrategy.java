package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.beans.factory.annotation.Autowired;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;

public final class BacklogPRStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem && item2.type == RelateItemTypeEnum.PR
        || item2.type == RelateItemTypeEnum.Backlogitem && item1.type == RelateItemTypeEnum.PR){
            return true;
        }
        return false;
    }

    @Override
    public void relate(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem){
            backlogItemRepository.associateWithProductRequirement(item1.id, item2.id);
        }
        else{
            backlogItemRepository.associateWithProductRequirement(item2.id, item1.id);
        }
    }
}

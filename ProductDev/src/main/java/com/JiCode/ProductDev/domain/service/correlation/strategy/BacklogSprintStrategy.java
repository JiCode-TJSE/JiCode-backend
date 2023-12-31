package com.JiCode.ProductDev.domain.service.correlation.strategy;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;

public final class BacklogSprintStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem && item2.type == RelateItemTypeEnum.Sprint
        || item2.type == RelateItemTypeEnum.Backlogitem && item1.type == RelateItemTypeEnum.Sprint){
            return true;
        }
        return false;
    }

    @Override
    public void relate(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem){
            backlogItemRepository.insertSrint(item1.id, item2.id);
        }
        else{
            backlogItemRepository.insertSrint(item2.id, item1.id);
        }
    }
}

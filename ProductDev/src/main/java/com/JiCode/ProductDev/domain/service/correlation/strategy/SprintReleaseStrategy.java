package com.JiCode.ProductDev.domain.service.correlation.strategy;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;

public final class SprintReleaseStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Sprint && item2.type == RelateItemTypeEnum.Release
        || item2.type == RelateItemTypeEnum.Sprint && item1.type == RelateItemTypeEnum.Release){
            return true;
        }
        return false;
    }

    @Override
    public void relate(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Sprint){
            sprintRepository.associateWithRelease(item1.id, item2.id);
        }
        else{
            sprintRepository.associateWithRelease(item2.id, item1.id);
        }
    }
}

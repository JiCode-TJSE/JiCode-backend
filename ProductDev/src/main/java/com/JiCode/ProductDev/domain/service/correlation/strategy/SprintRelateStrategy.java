package com.JiCode.ProductDev.domain.service.correlation.strategy;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;

public class SprintRelateStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Sprint || item2.type == RelateItemTypeEnum.Sprint){
            return true;
        }
        return false;
    }

    @Override
    protected void checkSuccessor(RelateBo item1, RelateBo item2)
    {
        if(backlogReleaseStrategy.testType(item1, item2)){
            setSuccessor(backlogReleaseStrategy);
        }
        else if(sprintReleaseStrategy.testType(item1, item2)){
            setSuccessor(sprintReleaseStrategy);
        }
    }
}

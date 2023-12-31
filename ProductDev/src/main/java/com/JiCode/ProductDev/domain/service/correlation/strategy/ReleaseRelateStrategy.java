package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;

@Service
public class ReleaseRelateStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Release || item2.type == RelateItemTypeEnum.Release){
            return true;
        }
        return false;
    }

    @Override
    protected void checkSuccessor(RelateBo item1, RelateBo item2)
    {
        BacklogReleaseStrategy backlogReleaseStrategy = new BacklogReleaseStrategy();
        SprintReleaseStrategy sprintReleaseStrategy = new SprintReleaseStrategy();
        
        if(backlogReleaseStrategy.testType(item1, item2)){
            setSuccessor(backlogReleaseStrategy);
        }
        else if(sprintReleaseStrategy.testType(item1, item2)){
            setSuccessor(sprintReleaseStrategy);
        }
    }
}

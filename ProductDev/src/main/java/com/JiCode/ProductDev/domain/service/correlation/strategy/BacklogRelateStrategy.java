package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;

@Service
public class BacklogRelateStrategy extends RelateStrategy {
    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem || item2.type == RelateItemTypeEnum.Backlogitem){
            return true;
        }
        return false;
    }
    
    @Override
    protected void checkSuccessor(RelateBo item1, RelateBo item2)
    {
        BacklogBacklogStrategy backlogBacklogStrategy = new BacklogBacklogStrategy();
        BacklogPRStrategy backlogPRStrategy = new BacklogPRStrategy();
        BacklogReleaseStrategy backlogReleaseStrategy = new BacklogReleaseStrategy();
        BacklogSprintStrategy backlogSprintStrategy = new BacklogSprintStrategy();

        if(backlogBacklogStrategy.testType(item1, item2)){
            setSuccessor(backlogBacklogStrategy);
        }
        else if(backlogPRStrategy.testType(item1, item2)){
            setSuccessor(backlogPRStrategy);
        }
        else if(backlogReleaseStrategy.testType(item1, item2)){
            setSuccessor(backlogReleaseStrategy);
        }
        else if(backlogSprintStrategy.testType(item1, item2)){
            setSuccessor(backlogReleaseStrategy);
        }
    }
}

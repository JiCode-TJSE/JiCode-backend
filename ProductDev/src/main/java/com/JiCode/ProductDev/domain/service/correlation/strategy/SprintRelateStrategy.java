package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.SprintRepository;

@Service
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
        BacklogSprintStrategy backlogSprintStrategy = new BacklogSprintStrategy();
        SprintReleaseStrategy sprintReleaseStrategy = new SprintReleaseStrategy();
        if(backlogSprintStrategy.testType(item1, item2)){
            setSuccessor(backlogSprintStrategy);
        }
        else if(sprintReleaseStrategy.testType(item1, item2)){
            setSuccessor(sprintReleaseStrategy);
        }
    }
}

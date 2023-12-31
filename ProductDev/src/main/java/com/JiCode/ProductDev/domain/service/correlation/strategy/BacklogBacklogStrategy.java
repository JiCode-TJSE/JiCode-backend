package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;

@Service
public final class BacklogBacklogStrategy extends RelateStrategy {
    @Autowired
    BacklogItemRepository backlogItemRepository;

    @Override
    public boolean testType(RelateBo item1, RelateBo item2)
    {
        if(item1.type == RelateItemTypeEnum.Backlogitem && item2.type == RelateItemTypeEnum.Backlogitem){
            return true;
        }
        return false;
    }

    @Override
    public void relate(RelateBo item1, RelateBo item2)
    {
        backlogItemRepository.associateWithBacklogItem(item1.id, item2.id);
    }
}

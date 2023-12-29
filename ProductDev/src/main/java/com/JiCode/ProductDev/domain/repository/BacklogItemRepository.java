package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface BacklogItemRepository {
    public BacklogItemAggregation selectById(String id);

    public PageInfo<BacklogItemAggregation> getPage(int pageNum, int pageSize);

    public int insert(BacklogItemAggregation backlogItemAggregation);

    public int updateById(BacklogItemAggregation backlogItemAggregation);

    public int deleteById(String id);

    public int associateWithBacklogItem(String backlogItemId1,String backlogItemId2);

    public int associateWithProductRequirement(String backlogItemId,String productRequirementId);

    public int associateWithSprint(String backlogItemId,String sprintId);
}

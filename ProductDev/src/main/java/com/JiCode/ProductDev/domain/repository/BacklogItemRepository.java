package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface BacklogItemRepository {
    public BacklogItemAggregation selectById(String id);

    public List<BacklogItemAggregation> selectAll();

    public int insert(BacklogItemAggregation backlogItemAggregation);

    public int updateById(BacklogItemAggregation backlogItemAggregation);

    public int deleteById(String id);

    public int associateWithBacklogItem(String backlogItemId1,String backlogItemId2) throws InsertFailureException;

    public int associateWithProductRequirement(String backlogItemId,String productRequirementId);

    public int associateWithSprint(String backlogItemId,List<String> sprintId);

    public int insertSrint(String backlogItemId,String sprintId);

    public int associateWithRelease(String backlogItemId,List<String> releaseId);
    public int insertRelease(String backlogItemId,String releaseId);

    public int cancelAssociate(String backlogItemId1,String backlogItemId2);
}

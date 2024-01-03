package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.JiCode.ProductDev.domain.model.value.ReleaseStage;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReleaseRepository {
    public ReleaseAggregation selectById(String id);
    public PageInfo<ReleaseAggregation> getPage(int pageNum, int pageSize);

    public List<ReleaseAggregation> selectAll();

    public int insert(ReleaseAggregation releaseAggregation);

     public int update(ReleaseAggregation releaseAggregation);

     public int deleteById(String id);
     public int insertBacklogItem(String releaseId,String backlogItemId);

     public int associateWithBacklogItem(String releaseId, List<String> backlogItemIds);

}

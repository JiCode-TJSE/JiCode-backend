package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ReleaseAggregation;
import com.github.pagehelper.PageInfo;

public interface ReleaseRepository {
    public ReleaseAggregation selectById(String id);
    // public PageInfo<ReleaseAggregation> getPage(int pageNum, int pageSize);

    public int insert(ReleaseAggregation releaseAggregation);

     public int update(ReleaseAggregation releaseAggregation);
}

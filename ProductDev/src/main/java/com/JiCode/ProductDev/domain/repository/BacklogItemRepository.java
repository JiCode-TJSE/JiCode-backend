package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface BacklogItemRepository {
    public BacklogItemAggregation selectById(String id);

    public PageInfo<BacklogItemAggregation> selectAll(int pageNum, int pageSize);

    // public int insert(String id, String priority, Date startTime, Date endTime, String source, String type, String description, String projectId, String managerId, String scheduleId);
}

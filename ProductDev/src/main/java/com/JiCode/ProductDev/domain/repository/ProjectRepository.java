package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProjectRepository {
    public ProjectAggregation selectById(String id);
    public PageInfo<ProjectAggregation> selectAll(int pageNum, int pageSize);
}

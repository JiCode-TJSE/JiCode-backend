package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface ProjectRepository {
    public ProjectAggregation selectById(String id);
    public PageInfo<ProjectAggregation> selectAll(int pageNum, int pageSize);
    public int insert(String id, String status, Float progress, Date startTime, Date endTime, String managerId);
    public int updateById(String id, String status, Float progress, Date startTime, Date endTime, String managerId);
    public int deleteById(String id);
}

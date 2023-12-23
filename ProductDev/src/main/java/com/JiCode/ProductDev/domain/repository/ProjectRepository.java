package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface ProjectRepository {
    //public copy(ProjectAggregation projectAggregation, Project project);
    public int saveAggregate(ProjectAggregation projectAggregation);
    public ProjectAggregation selectById(String id);
    public PageInfo<ProjectAggregation> getPage(int pageNum, int pageSize);
    public int insert(ProjectAggregation projectAggregation);
    public int updateById(ProjectAggregation projectAggregation);
    public int deleteById(String id);
}

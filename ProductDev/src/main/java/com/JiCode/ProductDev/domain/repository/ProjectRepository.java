package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * 仓储类，用于对数据库进行操作
 * @author Laurent Wu
 * @date 2023/12/24
 */


public interface ProjectRepository {
    public ProjectAggregation selectById(String id);

    public List<ProjectAggregation> selectAll();
    public int insert(ProjectAggregation projectAggregation);
    public int updateById(ProjectAggregation projectAggregation);
    public int deleteById(String id);
}

package com.JiCode.ProductDev.domain.repository;

import com.JiCode.ProductDev.domain.model.ProjectAggregation;

import java.util.List;

public interface ProjectRepository {
    public ProjectAggregation selectById(String id);
}

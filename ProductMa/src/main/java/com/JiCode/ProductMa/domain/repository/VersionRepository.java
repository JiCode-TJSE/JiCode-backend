package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.VersionAggregation;

public interface VersionRepository {
    public void insert(VersionAggregation versionAggregation) throws Exception;

    public void delete(String id) throws Exception;

    public void update(VersionAggregation versionAggregation) throws Exception;

    public VersionAggregation selectById(String id) throws Exception;
}

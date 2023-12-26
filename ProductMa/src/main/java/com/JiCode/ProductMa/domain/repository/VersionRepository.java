package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;

public interface VersionRepository {
    public void insert(VersionAggregation versionAggregation) throws InsertFailedException;

    public void delete(String id) throws DeleteFailedException;

    public void update(VersionAggregation versionAggregation) throws UpdateFailedException;

    public VersionAggregation selectById(String id) throws SelectFailedException;
}

package com.JiCode.ProductMa.domain.repository;


import com.JiCode.ProductMa.domain.model.TestAggregation;

public interface TestRepository {
    public TestAggregation selectById(String id);
}

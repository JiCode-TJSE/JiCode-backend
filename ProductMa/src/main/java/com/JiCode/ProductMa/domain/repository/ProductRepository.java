package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.ProductAggregation;


/**
 * 资源库，直接对数据库操作
 */
public interface ProductRepository {
    public ProductAggregation selectById(String id) throws Exception;

    public void insert(ProductAggregation productAggregation) throws Exception;

    public void update(ProductAggregation productAggregation) throws Exception;

    public void delete(String id) throws Exception;
}

package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;

import java.util.List;


/**
 * 资源库，直接对数据库操作
 */
public interface ProductRepository {
    public ProductAggregation selectById(String id) throws SelectFailedException;

    public String insert(ProductAggregation productAggregation) throws InsertFailedException;

    public void update(ProductAggregation productAggregation) throws UpdateFailedException, InsertFailedException;

    public void delete(String id) throws DeleteFailedException;

    public List<String> selectByAccountId(String accountId) throws SelectFailedException;
}

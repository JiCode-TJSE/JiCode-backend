package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.NotFoundException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClientRepository {
    //按ClientID查找
    public ClientAggregation selectById(String id) throws NotFoundException;

    //按productID查找分组
    public List<ClientAggregation> selectByProductId(String productId) throws Exception;

    public void insert(ClientAggregation clientAggregation) throws InsertFailedException;

    public void update(ClientAggregation clientAggregation) throws UpdateFailedException;

    public void delete(String id) throws DeleteFailedException;
}

package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.exception.client.repository.ClientNotFoundException;
import com.JiCode.ProductMa.exception.client.repository.DeleteClientFailedException;
import com.JiCode.ProductMa.exception.client.repository.InsertClientFailedException;
import com.JiCode.ProductMa.exception.client.repository.UpdateClientFailedException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClientRepository {
    //按ClientID查找
    public ClientAggregation selectById(String id) throws ClientNotFoundException;

    //按productId查找分组
    public List<ClientAggregation> selectByProductId(String productId) throws Exception;

    public PageInfo<ClientAggregation> getPage(int pageNum, int pageSize);

    public void insert(ClientAggregation clientAggregation) throws InsertClientFailedException;

    public void update(ClientAggregation clientAggregation) throws UpdateClientFailedException;

    public void delete(String id) throws DeleteClientFailedException;
}

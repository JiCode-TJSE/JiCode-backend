package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.exception.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClientRepository {
    //按ClientID查找
    public ClientAggregation selectById(String id) throws NotFoundException;

    //按productID查找分组
    public List<ClientAggregation> selectByProductId(String productId) throws NotFoundException;

    public String insert(ClientAggregation clientAggregation) throws InsertFailedException;

    public void update(ClientAggregation clientAggregation) throws UpdateFailedException;

    public void delete(String id) throws DeleteFailedException;

    public List<ClientAggregation> selectByPage(String productId, int pageNo, int pageSize)
            throws SelectFailedException;

    public String[] selectNamesById(String[] clientIds)
        throws NotFoundException;

    //按客户名查找客户列表
    public List<ClientAggregation> selectByClientName(String keyword, String productId) throws NotFoundException;
}

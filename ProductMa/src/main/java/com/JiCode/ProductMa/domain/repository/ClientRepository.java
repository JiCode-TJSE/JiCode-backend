package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.github.pagehelper.PageInfo;

public interface ClientRepository {
    public ClientAggregation selectById(String id);
    public PageInfo<ClientAggregation> getPage(int pageNum, int pageSize);
    public int insert(ClientAggregation clientAggregation);
    public int updateById(ClientAggregation clientAggregation);
    public int deleteById(String id);
}

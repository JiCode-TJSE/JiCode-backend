package com.JiCode.ProductMa.domain.repository;

import com.JiCode.ProductMa.exception.NotFoundException;
import com.JiCode.ProductMa.exception.SelectFailedException;

/**
 * 调用TeamMapper查询TeamId对应的TeamName
 * 目前后台管理微服务还没写，先在这里用一下
 */
public interface OutsideRepository {
    public String selectTeamNameById(String id) throws SelectFailedException;
}

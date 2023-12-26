package com.JiCode.ProductMa.application;

import com.JiCode.ProductMa.application.dto.AllrequirementsDto;
import com.JiCode.ProductMa.exception.ServerException;

public interface RequirementApplication {

    /**
     * 获取某个产品的需求列表
     */
    public AllrequirementsDto getAllRequirementsByProductId(String productId, int pageNo, int pageSize)
            throws ServerException;
}

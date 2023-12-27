package com.JiCode.ProductMa.application;

import java.util.Map;

import com.JiCode.ProductMa.application.dto.AddRequirementReqDto;
import com.JiCode.ProductMa.application.dto.AllrequirementsDto;
import com.JiCode.ProductMa.exception.ServerException;

public interface RequirementApplication {

    /**
     * 获取某个产品的需求列表
     */
    public AllrequirementsDto getAllRequirementsByProductId(String productId, int pageNo, int pageSize)
            throws ServerException;

    /**
     * 新增需求
     */
    public Map<String, String> createRequirement(AddRequirementReqDto addRequirementReqDto) throws ServerException;
}
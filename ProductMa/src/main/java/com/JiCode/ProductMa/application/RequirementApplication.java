package com.JiCode.ProductMa.application;

import java.util.Map;

import com.JiCode.ProductMa.application.dto.AddRequirementReqDto;
import com.JiCode.ProductMa.application.dto.AddVersionReqDto;
import com.JiCode.ProductMa.application.dto.RequirementArrResDto;
import com.JiCode.ProductMa.application.dto.RequirementDetailResDto;
import com.JiCode.ProductMa.application.dto.UpdateRequirementReqDto;
import com.JiCode.ProductMa.application.dto.UpdateVersionReqDto;
import com.JiCode.ProductMa.exception.ServerException;

public interface RequirementApplication {

    /**
     * 获取某个产品的需求列表
     */
    public RequirementArrResDto getAllRequirementsByProductId(String productId, int pageNo, int pageSize)
            throws ServerException;

    /**
     * 新增需求
     */
    public Map<String, String> createRequirement(AddRequirementReqDto addRequirementReqDto) throws ServerException;

    /**
     * 删除需求
     */
    public void deleteRequirement(String requirementId) throws ServerException;

    /**
     * 获取需求详情
     */
    public RequirementDetailResDto getRequirementDetail(String requirementId) throws ServerException;

    /**
     * 切换版本
     */
    public void switchVersion(String versionId, String requirementId) throws ServerException;

    /**
     * 更新需求
     */
    public void updateRequirement(UpdateRequirementReqDto updateRequirementReqDto) throws ServerException;

    /**
     * 新增版本
     */
    public Map<String, String> addVersion(AddVersionReqDto addVersionReqDto) throws ServerException;

    /**
     * 更新版本
     */
    public void updateVersion(UpdateVersionReqDto updateVersionReqDto) throws ServerException;
}

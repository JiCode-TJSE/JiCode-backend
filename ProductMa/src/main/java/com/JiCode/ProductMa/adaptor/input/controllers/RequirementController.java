package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.adaptor.input.vo.CommonVo;
import com.JiCode.ProductMa.adaptor.input.vo.GetShowingRequirementVo;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Product;
import com.JiCode.ProductMa.application.ProductApplication;
import com.JiCode.ProductMa.application.RequirementApplication;
import com.JiCode.ProductMa.application.dto.AddRequirementReqDto;
import com.JiCode.ProductMa.application.dto.AddVersionReqDto;
import com.JiCode.ProductMa.application.dto.AllProductsDto;
import com.JiCode.ProductMa.application.dto.ProductResponseDto;
import com.JiCode.ProductMa.application.dto.RequirementArrResDto;
import com.JiCode.ProductMa.application.dto.RequirementDetailResDto;
import com.JiCode.ProductMa.application.dto.UpdateRequirementReqDto;
import com.JiCode.ProductMa.application.dto.UpdateVersionReqDto;
import com.JiCode.ProductMa.common.CodeEnum;
import com.JiCode.ProductMa.exception.ServerException;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class RequirementController {

    @Autowired
    RequirementApplication requirementApplication;

    @Autowired
    ProductApplication productApplication;

    @GetMapping("/requirments")
    public CommonVo<RequirementArrResDto> getAllRequirements(@RequestParam("productId") String productId,
            @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) throws ServerException {
        RequirementArrResDto allrequirementsDto = requirementApplication.getAllRequirementsByProductId(productId,
                pageNo,
                pageSize);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, allrequirementsDto);
    }

    @PostMapping("/requirement")
    public CommonVo<Map<String, String>> createRequirement(@RequestBody AddRequirementReqDto addRequirementReqDto)
            throws ServerException {
        Map<String, String> data = requirementApplication.createRequirement(addRequirementReqDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, data);
    }

    @DeleteMapping("/requirement")
    public CommonVo<Void> deleteRequirement(@RequestParam("requirementId") String RequirementId)
            throws ServerException {
        requirementApplication.deleteRequirement(RequirementId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }

    @GetMapping("/requirement")
    public CommonVo<RequirementDetailResDto> getRequirementDetail(@RequestParam String requirementId)
            throws ServerException {
        RequirementDetailResDto requirementDetailResDto = requirementApplication.getRequirementDetail(requirementId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, requirementDetailResDto);
    }

    @PutMapping("/version/switch")
    public CommonVo<Void> switchVersion(@RequestParam("versionId") String versionId,
            @RequestParam("requirementId") String requirementId)
            throws ServerException {
        requirementApplication.switchVersion(versionId, requirementId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }

    @PutMapping("/requirement")
    public CommonVo<Void> updateRequirement(@RequestBody UpdateRequirementReqDto updateRequirementReqDto)
            throws ServerException {
        requirementApplication.updateRequirement(updateRequirementReqDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }

    @PostMapping("/version")
    public CommonVo<Map<String, String>> addVersion(@RequestBody AddVersionReqDto addVersionReqDto)
            throws ServerException {
        Map<String, String> data = requirementApplication.addVersion(addVersionReqDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, data);
    }

    @PutMapping("/version")
    public CommonVo<Void> updateVersion(@RequestBody UpdateVersionReqDto updateVersionReqDto) throws ServerException {
        requirementApplication.updateVersion(updateVersionReqDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }

    @GetMapping("/requirements/show")
    public CommonVo<GetShowingRequirementVo> getShowingRequirements(@RequestHeader String Authorization)
            throws ServerException {
        GetShowingRequirementVo getShowingRequirementVo = new GetShowingRequirementVo();
        // 先拿到所有的 productId，然后循环调用
        AllProductsDto allProductsDto = productApplication.getAllProductsByAccountId(Authorization);
        ProductResponseDto[] productResponseDtos = allProductsDto.getRecords();
        for (ProductResponseDto productResponseDto : productResponseDtos) {
            String productId = productResponseDto.getId();
            RequirementArrResDto requirementArrResDto = requirementApplication.getAllRequirementsByProductId(productId,
                    1, 5);
            for (RequirementArrResDto.Record record : requirementArrResDto.getRecords()) {
                getShowingRequirementVo.showingDatas.add(new GetShowingRequirementVo.ShowingData(
                        productResponseDto.getTitle(), record.getTypeEnum(), record.getName()));
            }
        }
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, getShowingRequirementVo);
    }

}

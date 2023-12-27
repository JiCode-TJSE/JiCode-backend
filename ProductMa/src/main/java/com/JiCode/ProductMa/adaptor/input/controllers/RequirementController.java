package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.adaptor.input.vo.CommonVo;
import com.JiCode.ProductMa.application.RequirementApplication;
import com.JiCode.ProductMa.application.dto.AddRequirementReqDto;
import com.JiCode.ProductMa.application.dto.AllrequirementsDto;
import com.JiCode.ProductMa.exception.ServerException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class RequirementController {

    @Autowired
    RequirementApplication requirementApplication;

    @GetMapping("/requirments")
    public CommonVo<AllrequirementsDto> getAllRequirements(@RequestParam("productId") String productId,
            @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) throws ServerException {
        AllrequirementsDto allrequirementsDto = requirementApplication.getAllRequirementsByProductId(productId, pageNo,
                pageSize);
        return CommonVo.create("请求成功", true, allrequirementsDto);
    }

    @PostMapping("/requirement")
    public CommonVo<Map<String, String>> postMethodName(@RequestBody AddRequirementReqDto addRequirementReqDto)
            throws ServerException {
        Map<String, String> data = requirementApplication.createRequirement(addRequirementReqDto);
        return CommonVo.create("请求成功", true, data);
    }

}

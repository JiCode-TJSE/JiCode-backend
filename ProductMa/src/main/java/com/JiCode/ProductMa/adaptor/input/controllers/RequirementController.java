package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.application.RequirementApplication;
import com.JiCode.ProductMa.application.dto.AllrequirementsDto;
import com.JiCode.ProductMa.exception.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class RequirementController {

    @Autowired
    RequirementApplication requirementApplication;

    @GetMapping("/requirments")
    public AllrequirementsDto getAllRequirements(@RequestParam("productId") String productId,
            @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) throws ServerException {
        return requirementApplication.getAllRequirementsByProductId(productId, pageNo, pageSize);
    }
}

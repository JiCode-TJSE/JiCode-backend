package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.application.Impl.TestApplicationImpl;
import com.JiCode.ProductMa.application.TestApplication;
import com.JiCode.ProductMa.application.dto.SelectTestByIdDto;
import com.JiCode.ProductMa.domain.model.TestAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProductDev")
public class TestController {

    @Autowired
    TestApplicationImpl testApplicationImpl;

    @GetMapping("/selectById")
    public SelectTestByIdDto selectTestById(@RequestParam("id") String id){
        SelectTestByIdDto selectTestByIdDto = testApplicationImpl.selectTestById(id);
        return selectTestByIdDto;
    }
}

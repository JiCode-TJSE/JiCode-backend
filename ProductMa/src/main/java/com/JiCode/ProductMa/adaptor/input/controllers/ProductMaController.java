package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.application.Impl.ProductMaApplicationImpl;
import com.JiCode.ProductMa.application.dto.ProductSimpleInfoArrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductMaController {

    @Autowired
    ProductMaApplicationImpl testApplicationImpl;

    @GetMapping("/get")
    public ProductSimpleInfoArrDTO getAllProductsByUserID(@RequestHeader("userID") String userID) {
        return testApplicationImpl.getAllProductsByUserID(userID);
    }
}

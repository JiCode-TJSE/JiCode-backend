package com.JiCode.ProductMa.adaptor.input.controllers;


import com.JiCode.ProductMa.adaptor.input.vo.CommonVo;
import com.JiCode.ProductMa.application.ProductApplication;
import com.JiCode.ProductMa.application.dto.AllProductsDto;
import com.JiCode.ProductMa.application.dto.ClientDto;
import com.JiCode.ProductMa.application.dto.ProductRequestDto;
import com.JiCode.ProductMa.application.dto.ProductResponseDto;
import com.JiCode.ProductMa.common.CodeEnum;
import com.JiCode.ProductMa.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductApplication productApplication;

    //获取当前账号可见所有产品
    @GetMapping("/items")
    public CommonVo<AllProductsDto> getAllProducts(@RequestHeader("Authorization") String authorization)
        throws ServerException{
        AllProductsDto responseData = productApplication.getAllProductsByAccountId(authorization); //实际是userID
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }


    // 获取产品
    @GetMapping("/item")
    public CommonVo<ProductResponseDto> getProductDetail(@RequestParam("id") String productId)
            throws ServerException {
        ProductResponseDto responseData = productApplication.getProductDetail(productId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }


    // 新建产品
    @PostMapping("/item")
    public CommonVo<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto)
            throws ServerException {
        ProductResponseDto responseData = productApplication.createProduct(requestDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }


    // 删除产品
    @DeleteMapping("/item")
    public CommonVo<Void> deleteProduct(@RequestParam("id") String productId)
            throws ServerException {
        productApplication.deleteProduct(productId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }


    // 更新产品信息
    @PutMapping("/item")
    public CommonVo<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto requestDto)
            throws ServerException {
        ProductResponseDto responseData = productApplication.updateProduct(requestDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }

}

package com.JiCode.ProductMa.application;

import com.JiCode.ProductMa.application.dto.AllProductsDto;
import com.JiCode.ProductMa.application.dto.ProductResponseDto;
import com.JiCode.ProductMa.application.dto.ProductRequestDto;
import com.JiCode.ProductMa.exception.ServerException;

public interface ProductApplication {

    //新增产品 (test ok)
    public ProductResponseDto createProduct(ProductRequestDto productReponseDto)
        throws ServerException;


    //获取产品详情 (test ok)
    public ProductResponseDto getProductDetail(String productId)
        throws ServerException;


    //删除产品 (test ok)
    public void deleteProduct(String productId)
        throws ServerException;


    //更新产品 (test ok)
    public ProductResponseDto updateProduct(ProductRequestDto request)
        throws ServerException;


    //获取产品列表（根据account_id？所有成员都可以看到这个产品）
    public AllProductsDto getAllProductsByAccountId(String accountId)
        throws ServerException;

}

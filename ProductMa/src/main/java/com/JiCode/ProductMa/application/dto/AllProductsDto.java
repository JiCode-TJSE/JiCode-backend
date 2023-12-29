package com.JiCode.ProductMa.application.dto;

import lombok.Data;


/**
 * 分页请求：获取用户可见产品列表
 */
@Data
public class AllProductsDto {
    // 总产品数
    int total;

    //返回的结果
    ProductResponseDto[] records;

}

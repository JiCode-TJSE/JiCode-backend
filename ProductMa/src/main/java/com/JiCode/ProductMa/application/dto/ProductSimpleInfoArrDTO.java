package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class ProductSimpleInfoArrDTO {
    private ProductSimpleInfo[] products;

    @Data
    public static class ProductSimpleInfo {
        private String id;
        private String name;
    }
}
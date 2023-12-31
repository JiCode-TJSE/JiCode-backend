package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class SearchClientResponseDto {
    //结果总数
    int total;

    //结果列表
    Record[] records;

    @Data
    static public class Record {
        String id; //客户ID
        String name;//客户名
    }

}

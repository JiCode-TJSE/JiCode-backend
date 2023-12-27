package com.JiCode.ProductMa.application.dto;

import lombok.Data;


/**
 * 分页请求：获取产品对应客户列表-返回数据结构
 */
@Data
public class AllClientsDto {
    // 记录
    Record[] records;
    // 页大小
    int size;
    // 该页记录数
    int recordNum;
    // 总页数
    int pages;
    // 当前页
    int current;
    // 总记录数
    int total;

    @Data
    static public class Record {
        String name;
        String rank;
        String size;
        String type;
    }
}

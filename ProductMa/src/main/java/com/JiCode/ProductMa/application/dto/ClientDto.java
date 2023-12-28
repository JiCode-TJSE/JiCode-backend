package com.JiCode.ProductMa.application.dto;

import lombok.Data;


/**
 * 单独客户信息：更新/添加客户的返回数据结构
 * 更新客户信息的请求参数
 * 返回的数据结果
 */
@Data
public class ClientDto {
    //此处最好和ClientAggregation的属性一样
    String id;
    String rank;
    String size;
    String name;
    String type;
    String detail;
    //这里的product_id和ClientAggregation里的productId不同名
    //注意处理！！！
    String product_id;
}

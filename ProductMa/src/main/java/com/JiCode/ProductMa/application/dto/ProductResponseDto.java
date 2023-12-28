package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    String id; //产品ID
    String title;
    String detail;
    String mark;
    //需要调用OutsideRepository
    String team_name;
    String team_id;
}

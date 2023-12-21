package com.JiCode.ProductMa.application.dto;

import lombok.Data;

// 这边要写Data注解是因为要给这个赋值之类的
@Data
public class SelectTestByIdDto {
    public String id;
    public String name;
}

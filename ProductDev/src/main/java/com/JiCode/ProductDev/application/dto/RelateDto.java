package com.JiCode.ProductDev.application.dto;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelateDto {
    String id1;
    RelateItemTypeEnum type1;
    String id2;
    RelateItemTypeEnum type2;
}

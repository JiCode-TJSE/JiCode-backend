package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class AddRequirementReqDto {
    String productId;
    String name;
    String detail;
    String moduleEnum;
    String typeEnum;
    String supervisorId;
}

package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class AddRequirementReqDto {
    String belongProductId;
    String name;
    String detail;
    String moduleEnum;
    String sourceEnum;
    String typeEnum;
    String supervisorId;
}

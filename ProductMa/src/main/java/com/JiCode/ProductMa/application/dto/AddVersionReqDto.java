package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class AddVersionReqDto {
    String requirementId;
    String name;
    String detail;
}

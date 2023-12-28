package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class UpdateVersionReqDto {
    String id;
    String requirementId;
    String name;
    String detail;
}

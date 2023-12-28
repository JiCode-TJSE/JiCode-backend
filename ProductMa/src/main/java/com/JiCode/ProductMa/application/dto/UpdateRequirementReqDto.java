package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class UpdateRequirementReqDto {

    String requirementId;
    String name;
    String detail;
    String moduleEnum;
    String sourceEnum;
    String typeEnum;
    String supervisorId;
    String[] clientArr;
    String[] backlogItemArr;
}

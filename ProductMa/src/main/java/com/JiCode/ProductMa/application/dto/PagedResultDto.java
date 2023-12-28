package com.JiCode.ProductMa.application.dto;

import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;

import lombok.Data;

@Data
public class PagedResultDto {
    private RequirementEntity[] requirements;
    private int totalCount;
}

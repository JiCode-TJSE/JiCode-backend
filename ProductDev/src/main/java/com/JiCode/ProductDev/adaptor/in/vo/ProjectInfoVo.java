package com.JiCode.ProductDev.adaptor.in.vo;


import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfoVo {
    ProjectAggregation projectAggregation;
    Integer not_begin;
    Integer in_progress;
    Integer finished;


}

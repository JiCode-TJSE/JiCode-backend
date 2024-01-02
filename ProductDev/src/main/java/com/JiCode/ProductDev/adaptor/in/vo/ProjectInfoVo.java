package com.JiCode.ProductDev.adaptor.in.vo;


import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectInfoVo {
    ProjectAggregation projectAggregation;
    Integer not_begin;
    Integer in_progress;
    Integer finished;

    public ProjectInfoVo(ProjectAggregation projectAggregation, Integer not_begin, Integer in_progress, Integer finished) {
        this.projectAggregation = projectAggregation;
        this.not_begin = not_begin;
        this.in_progress = in_progress;
        this.finished = finished;
    }
}

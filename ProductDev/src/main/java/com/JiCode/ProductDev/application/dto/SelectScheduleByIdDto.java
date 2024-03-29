package com.JiCode.ProductDev.application.dto;

import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectScheduleByIdDto {
    String id;
    int estimatedWorkhour;
    int actualWorkhour;
    int remainWorkhour;
    float progress;

    List<WorkhourAggregation> workhourAggregations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstimatedWorkhour() {
        return estimatedWorkhour;
    }

    public void setEstimatedWorkhour(int estimatedWorkhour) {
        this.estimatedWorkhour = estimatedWorkhour;
    }

    public int getActualWorkhour() {
        return actualWorkhour;
    }

    public void setActualWorkhour(int actualWorkhour) {
        this.actualWorkhour = actualWorkhour;
    }

    public int getRemainWorkhour() {
        return remainWorkhour;
    }

    public void setRemainWorkhour(int remainWorkhour) {
        this.remainWorkhour = remainWorkhour;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}

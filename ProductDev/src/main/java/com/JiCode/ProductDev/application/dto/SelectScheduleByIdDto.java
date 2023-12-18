package com.JiCode.ProductDev.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectScheduleByIdDto {
    String id;
    int estimated_workhour;
    int actual_workhour;
    int remain_workhour;
    float progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstimated_workhour() {
        return estimated_workhour;
    }

    public void setEstimated_workhour(int estimated_workhour) {
        this.estimated_workhour = estimated_workhour;
    }

    public int getActual_workhour() {
        return actual_workhour;
    }

    public void setActual_workhour(int actual_workhour) {
        this.actual_workhour = actual_workhour;
    }

    public int getRemain_workhour() {
        return remain_workhour;
    }

    public void setRemain_workhour(int remain_workhour) {
        this.remain_workhour = remain_workhour;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}

package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

import lombok.Data;

@Data
public class Schedule {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEstimated_workhour() {
        return estimated_workhour;
    }

    public void setEstimated_workhour(Integer estimated_workhour) {
        this.estimated_workhour = estimated_workhour;
    }

    public Integer getActual_workhour() {
        return actual_workhour;
    }

    public void setActual_workhour(Integer actual_workhour) {
        this.actual_workhour = actual_workhour;
    }

    public Integer getRemain_workhour() {
        return remain_workhour;
    }

    public void setRemain_workhour(Integer remain_workhour) {
        this.remain_workhour = remain_workhour;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    private Integer estimated_workhour;
    private Integer actual_workhour;
    private Integer remain_workhour;
    private float progress;

}

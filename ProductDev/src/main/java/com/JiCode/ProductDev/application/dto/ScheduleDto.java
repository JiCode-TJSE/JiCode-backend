package com.JiCode.ProductDev.application.dto;

public class ScheduleDto {
    private String id;
    private Integer estimatedWorkhour;
    private Integer actualWorkhour;
    private Integer remainWorkhour;
    private float progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEstimatedWorkhour() {
        return estimatedWorkhour;
    }

    public void setEstimatedWorkhour(Integer estimatedWorkhour) {
        this.estimatedWorkhour = estimatedWorkhour;
    }

    public Integer getActualWorkhour() {
        return actualWorkhour;
    }

    public void setActualWorkhour(Integer actualWorkhour) {
        this.actualWorkhour = actualWorkhour;
    }

    public Integer getRemainWorkhour() {
        return remainWorkhour;
    }

    public void setRemainWorkhour(Integer remainWorkhour) {
        this.remainWorkhour = remainWorkhour;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}

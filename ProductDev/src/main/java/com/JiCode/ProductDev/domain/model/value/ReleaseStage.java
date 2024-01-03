package com.JiCode.ProductDev.domain.model.value;

import java.util.Date;

import com.JiCode.ProductDev.common.ReleaseStageEnum;

public class ReleaseStage {
    ReleaseStageEnum stage;
    Date date;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReleaseStageEnum getStage() {
        return stage;
    }

    public void setStage(ReleaseStageEnum stage) {
        this.stage = stage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

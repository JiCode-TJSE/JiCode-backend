package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

import java.util.Date;

public class RequirementVersion {
    private String id;

    private String name;

    private Date createTime;

    private String detail;

    private String belongRequirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getBelongRequirementId() {
        return belongRequirementId;
    }

    public void setBelongRequirementId(String belongRequirementId) {
        this.belongRequirementId = belongRequirementId == null ? null : belongRequirementId.trim();
    }
}
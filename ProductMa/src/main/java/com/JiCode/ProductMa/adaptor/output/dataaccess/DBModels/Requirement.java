package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class Requirement {
    private String id;

    private String belongProductId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBelongProductId() {
        return belongProductId;
    }

    public void setBelongProductId(String belongProductId) {
        this.belongProductId = belongProductId == null ? null : belongProductId.trim();
    }
}
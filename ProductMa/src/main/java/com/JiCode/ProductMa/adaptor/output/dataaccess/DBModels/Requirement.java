package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class Requirement {
    private String requirementid;

    private String belongProductId;

    private String requirementcontentid;

    public String getRequirementid() {
        return requirementid;
    }

    public void setRequirementid(String requirementid) {
        this.requirementid = requirementid == null ? null : requirementid.trim();
    }

    public String getBelongProductId() {
        return belongProductId;
    }

    public void setBelongProductId(String belongProductId) {
        this.belongProductId = belongProductId == null ? null : belongProductId.trim();
    }

    public String getRequirementcontentid() {
        return requirementcontentid;
    }

    public void setRequirementcontentid(String requirementcontentid) {
        this.requirementcontentid = requirementcontentid == null ? null : requirementcontentid.trim();
    }
}
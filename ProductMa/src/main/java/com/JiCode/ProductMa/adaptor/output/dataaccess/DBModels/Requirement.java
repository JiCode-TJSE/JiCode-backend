package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class Requirement {
    private String requirementId;

    private String belongProductId;

    private String requirementContentId;

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    public String getBelongProductId() {
        return belongProductId;
    }

    public void setBelongProductId(String belongProductId) {
        this.belongProductId = belongProductId == null ? null : belongProductId.trim();
    }

    public String getRequirementContentId() {
        return requirementContentId;
    }

    public void setRequirementContentId(String requirementContentId) {
        this.requirementContentId = requirementContentId == null ? null : requirementContentId.trim();
    }
}
package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class RequirementBacklogitemKey {
    private String requirementContentId;

    private String backlogitemId;

    public String getRequirementContentId() {
        return requirementContentId;
    }

    public void setRequirementContentId(String requirementContentId) {
        this.requirementContentId = requirementContentId == null ? null : requirementContentId.trim();
    }

    public String getBacklogitemId() {
        return backlogitemId;
    }

    public void setBacklogitemId(String backlogitemId) {
        this.backlogitemId = backlogitemId == null ? null : backlogitemId.trim();
    }
}
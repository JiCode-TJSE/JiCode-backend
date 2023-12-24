package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class RequirementBacklogitemKey {
    private String requirementId;

    private String backlogitemId;

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    public String getBacklogitemId() {
        return backlogitemId;
    }

    public void setBacklogitemId(String backlogitemId) {
        this.backlogitemId = backlogitemId == null ? null : backlogitemId.trim();
    }
}
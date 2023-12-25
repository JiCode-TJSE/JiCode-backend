package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class RequirementClientKey {
    private String requirementContentId;

    private String clientId;

    public String getRequirementContentId() {
        return requirementContentId;
    }

    public void setRequirementContentId(String requirementContentId) {
        this.requirementContentId = requirementContentId == null ? null : requirementContentId.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }
}
package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class RequirementClientKey {
    private String requirementId;

    private String clientId;

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }
}
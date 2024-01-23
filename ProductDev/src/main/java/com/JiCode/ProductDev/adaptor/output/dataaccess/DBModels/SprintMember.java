package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class SprintMember {
    private String sprintId;

    private String accountId;

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId == null ? null : sprintId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}
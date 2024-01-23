package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class BacklogitemMemberKey {
    private String backlogitemId;

    private String accountId;

    public String getBacklogitemId() {
        return backlogitemId;
    }

    public void setBacklogitemId(String backlogitemId) {
        this.backlogitemId = backlogitemId == null ? null : backlogitemId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}
package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class BacklogitemBacklogitem {
    private String backlogitemid1;

    private String backlogitemid2;

    public String getBacklogitemid1() {
        return backlogitemid1;
    }

    public void setBacklogitemid1(String backlogitemid1) {
        this.backlogitemid1 = backlogitemid1 == null ? null : backlogitemid1.trim();
    }

    public String getBacklogitemid2() {
        return backlogitemid2;
    }

    public void setBacklogitemid2(String backlogitemid2) {
        this.backlogitemid2 = backlogitemid2 == null ? null : backlogitemid2.trim();
    }
}
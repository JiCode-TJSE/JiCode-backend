package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class BacklogitemRelease {
    private String backlogitemId;

    private String releaseId;

    public String getBacklogitemId() {
        return backlogitemId;
    }

    public void setBacklogitemId(String backlogitemId) {
        this.backlogitemId = backlogitemId == null ? null : backlogitemId.trim();
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId == null ? null : releaseId.trim();
    }
}
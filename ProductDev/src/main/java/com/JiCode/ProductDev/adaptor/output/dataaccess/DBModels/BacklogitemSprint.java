package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

public class BacklogitemSprint {
    private String backlogitemId;

    private String sprintId;

    public String getBacklogitemId() {
        return backlogitemId;
    }

    public void setBacklogitemId(String backlogitemId) {
        this.backlogitemId = backlogitemId == null ? null : backlogitemId.trim();
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId == null ? null : sprintId.trim();
    }
}
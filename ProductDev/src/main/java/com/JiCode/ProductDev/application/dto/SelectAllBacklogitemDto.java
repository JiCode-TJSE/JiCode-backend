package com.JiCode.ProductDev.application.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SelectAllBacklogitemDto {
    public String id;
    public String name;
    public String description;
    public String type;
    public String priority;
    public String status;
    public String estimate;
    public String remaining;
    public String projectId;
    public String productRequirementId;
    public String sprintId;
    public String releaseId;
    public String createTime;
    public String updateTime;
    public String version;


    @Override
    public String toString() {
        return "SelectAllBacklogitemDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", estimate='" + estimate + '\'' +
                ", remaining='" + remaining + '\'' +
                ", projectId='" + projectId + '\'' +
                ", productRequirementId='" + productRequirementId + '\'' +
                ", sprintId='" + sprintId + '\'' +
                ", releaseId='" + releaseId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

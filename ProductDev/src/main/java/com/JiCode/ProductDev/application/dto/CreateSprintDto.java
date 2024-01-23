package com.JiCode.ProductDev.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSprintDto {
    public String id;

    public Date startTime;

    public Date endTime;

    public String goal;

    public String type;

    public String projectId;

    public String managerId;

    public String releaseId;

    public String organizationId;

    public String topic;

    public List<String> memberIds;

    public List<String> backlogItemIds;

}

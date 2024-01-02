package com.JiCode.ProductDev.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectDto {

    public String id;

    public String status;

    public Float progress;

    public Date startTime;

    public Date endTime;

    public String managerId;
    public String topic;
    public String organizationId;

    public List<String> member;

    public String description;
}

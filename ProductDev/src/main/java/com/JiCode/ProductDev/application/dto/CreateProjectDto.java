package com.JiCode.ProductDev.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectDto {
    public String organizationId;
    public String topic;
    public String description;

}

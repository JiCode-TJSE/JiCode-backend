package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

import lombok.Data;

@Data
public class Schedule {
    private String id;
    private Integer estimated_workhour;
    private Integer actual_workhour;
    private Integer remain_workhour;
    private float progress;

}

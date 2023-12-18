package com.JiCode.ProductDev.adaptor.in.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVo {
    String id;
    int estimated_workhour;
    int actual_workhour;
    int remain_workhour;
    float progress;
}

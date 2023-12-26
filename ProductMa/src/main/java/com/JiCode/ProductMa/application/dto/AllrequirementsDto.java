package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class AllrequirementsDto {
    // 记录
    Record[] records;
    // 页大小
    int size;
    // 该页记录数
    int recordNum;
    // 总页数
    int pages;
    // 当前页
    int current;
    // 总记录数
    int total;

    @Data
    static public class Record {
        String requirementId;
        String name;
        String typeEnum;
        String supervisorName;
    }
}

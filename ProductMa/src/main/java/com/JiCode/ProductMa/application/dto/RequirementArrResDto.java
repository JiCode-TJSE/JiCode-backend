package com.JiCode.ProductMa.application.dto;

import lombok.Data;

@Data
public class RequirementArrResDto {
    public Record[] records;
    public int size;
    public int recordNum;
    public int pages;
    public int current;
    public int total;

    @Data
    static public class Record {
        String requirementId;
        String name;
        String typeEnum;
        String supervisorName;
    }
}

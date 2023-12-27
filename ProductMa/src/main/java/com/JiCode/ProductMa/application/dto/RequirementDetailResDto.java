package com.JiCode.ProductMa.application.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RequirementDetailResDto {

    String name;

    String detail;

    String moduleEnum;

    String sourceEnum;

    String typeEnum;

    Supervisor supervisor;

    Version[] versionArr;

    Client[] clientArr;

    BacklogItem[] backlogItemArr;

    @Data
    static public class Supervisor {
        String supervisorName;
        String supervisorId;
    }

    @Data
    static public class Version {
        String id;
        String name;
        String detail;
        Date createTime;
    }

    @Data
    static public class Client {
        String clientId;
        String name;
    }

    @Data
    static public class BacklogItem {
        String backlogItemId;
        String name;
    }
}

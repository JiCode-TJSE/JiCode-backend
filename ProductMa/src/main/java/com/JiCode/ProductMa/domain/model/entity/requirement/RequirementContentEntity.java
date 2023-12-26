package com.JiCode.ProductMa.domain.model.entity.requirement;

import lombok.Data;

@Data
public class VersionContentEntity {

    private String name;

    private String detail;

    private String belongProductID;

    private String supervisorID;

    // 所属模块
    private String moduleEnum;

    // 来源
    private String sourceEnum;

    // 类型
    private String typeEnum;

    // 附件（暂不用）
    private String attachment;
}

package com.JiCode.ProductMa.domain.model;

import java.util.Date;

import lombok.Getter;

// 这边不定义Entity而是aggregation主要是为了减小主聚合根大小吧
@Getter
public class VersionAggregation {

    private String id;

    private String name;

    private String description;

    private Date createTime;

    private VersionAggregation() {
    }

    public static VersionAggregation createVersionByAll(
            String id,
            String name,
            String description,
            Date createTime) {
        VersionAggregation agg = new VersionAggregation();
        agg.id = id;
        agg.name = name;
        agg.description = description;
        agg.createTime = createTime;
        return agg;
    }

}

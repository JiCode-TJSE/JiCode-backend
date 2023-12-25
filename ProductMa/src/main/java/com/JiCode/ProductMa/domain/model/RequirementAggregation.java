package com.JiCode.ProductMa.domain.model;

import lombok.Getter;

@Getter
public class RequirementAggregation {

    private String id;

    private String title;

    private String description;

    private String belongProductID;

    private String supervisorID;

    // 所属模块
    private String moduleType;

    // 来源
    private String sourceType;

    // 类型
    private String typeType;

    // 价值
    private String valueType;

    private String[] clientIDArr;

    private String[] backlogItemIDArr;

    private VersionAggregation[] versionArr;

    // 附件（暂不用）
    private String attachment;

    private RequirementAggregation() {
    }

    public static RequirementAggregation createRequirementByAll(
            String id,
            String title,
            String description,
            String belongProductID,
            String supervisorID,
            String moduleType,
            String sourceType,
            String typeType,
            String valueType,
            String[] clientIDArr,
            String[] backlogItemIDArr,
            VersionAggregation[] versionArr,
            String attachment) {
        RequirementAggregation agg = new RequirementAggregation();
        agg.id = id;
        agg.title = title;
        agg.description = description;
        agg.belongProductID = belongProductID;
        agg.supervisorID = supervisorID;
        agg.moduleType = moduleType;
        agg.sourceType = sourceType;
        agg.typeType = typeType;
        agg.valueType = valueType;
        agg.clientIDArr = clientIDArr;
        agg.backlogItemIDArr = backlogItemIDArr;
        agg.versionArr = versionArr;
        agg.attachment = attachment;
        return agg;
    }

}

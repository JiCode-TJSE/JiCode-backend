package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.model.entity.requirement.VersionContentEntity;

import lombok.Getter;

@Getter
public class RequirementAggregation {

    private String id;

    private String inVersion;

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

    private String[] clientIDArr;

    private String[] backlogItemIDArr;

    private VersionAggregation[] versionArr;

    // 附件（暂不用）
    private String attachment;

    /**
     * 脏标记
     */
    private boolean requirementDirty;
    private boolean contentDirty;
    private boolean clientDirty;
    private boolean backlogItemDirty;
    private boolean versionDirty;

    /**
     * @Description 清除所有脏标记
     */
    public void cleanDirty() {
        this.requirementDirty = false;
        this.clientDirty = false;
        this.backlogItemDirty = false;
        this.versionDirty = false;
        this.contentDirty = false;
    }

    public boolean isRequirementDirty() {
        return requirementDirty;
    }

    public boolean isClientDirty() {
        return clientDirty;
    }

    public boolean isBacklogItemDirty() {
        return backlogItemDirty;
    }

    public boolean isVersionDirty() {
        return versionDirty;
    }

    public boolean isContentDirty() {
        return contentDirty;
    }

    public void switchVersion(String versionId, VersionContentEntity versionContent, String[] backlogItemArr,
            String[] clientArr) {
        // 检查 versionId 是否在现在的 versionArr 中，这边领域层要维护自己的数据一致性，所以要在这里检查
        boolean versionIdExists = false;
        for (VersionAggregation versionAggregation : versionArr) {
            if (versionAggregation.getId().equals(versionId)) {
                versionIdExists = true;
                break;
            }
        }
        if (!versionIdExists) {
            throw new RuntimeException("versionId 不在现在的 versionArr 中");
        }
        this.inVersion = versionId;
        this.name = versionContent.getName();
        this.detail = versionContent.getDetail();
        this.supervisorID = versionContent.getSupervisorID();
        this.moduleEnum = versionContent.getModuleEnum();
        this.sourceEnum = versionContent.getSourceEnum();
        this.typeEnum = versionContent.getTypeEnum();
        this.backlogItemIDArr = backlogItemArr;
        this.clientIDArr = clientArr;
        this.attachment = versionContent.getAttachment();
    }

    private RequirementAggregation() {
    }

    public static RequirementAggregation createRequirementByAll(
            String id,
            String inVersion,
            String name,
            String detail,
            String belongProductID,
            String supervisorID,
            String moduleEnum,
            String sourceEnum,
            String typeEnum,
            String[] clientIDArr,
            String[] backlogItemIDArr,
            VersionAggregation[] versionArr,
            String attachment) {
        RequirementAggregation agg = new RequirementAggregation();
        agg.id = id;
        agg.inVersion = inVersion;
        agg.name = name;
        agg.detail = detail;
        agg.belongProductID = belongProductID;
        agg.supervisorID = supervisorID;
        agg.moduleEnum = moduleEnum;
        agg.sourceEnum = sourceEnum;
        agg.typeEnum = typeEnum;
        agg.clientIDArr = clientIDArr;
        agg.backlogItemIDArr = backlogItemIDArr;
        agg.versionArr = versionArr;
        agg.attachment = attachment;
        return agg;
    }

}

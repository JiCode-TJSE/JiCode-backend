package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class RequirementContent {
    private String versionContentId;

    private String name;

    private String detail;

    private String attachment;

    private String moduleEnum;

    private String sourceEnum;

    private String typeEnum;

    private String supervisorId;

    private String mark;

    public String getVersionContentId() {
        return versionContentId;
    }

    public void setVersionContentId(String versionContentId) {
        this.versionContentId = versionContentId == null ? null : versionContentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getModuleEnum() {
        return moduleEnum;
    }

    public void setModuleEnum(String moduleEnum) {
        this.moduleEnum = moduleEnum == null ? null : moduleEnum.trim();
    }

    public String getSourceEnum() {
        return sourceEnum;
    }

    public void setSourceEnum(String sourceEnum) {
        this.sourceEnum = sourceEnum == null ? null : sourceEnum.trim();
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum == null ? null : typeEnum.trim();
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId == null ? null : supervisorId.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}
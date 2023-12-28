package com.JiCode.ProductMa.domain.model;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CopyFailedException;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class VersionAggregation {

    private String id;

    private String name;

    private String detail;

    private Date createTime;

    private boolean dirty;

    public void cleanDirty() {
        dirty = false;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    private VersionAggregation() {
    }

    public <T> void copyPropertiesTo(T template) throws CopyFailedException {
        try {
            BeanUtils.copyProperties(this, template);
        } catch (Exception e) {
            throw new CopyFailedException("Failed to copy properties to template.", e);
        }
    }

    public static <T> VersionAggregation createVersionByAll(T template)
            throws CreateFailedException {
        VersionAggregation versionAgg = new VersionAggregation();
        BeanUtils.copyProperties(template, versionAgg);
        try {
            for (Field field : VersionAggregation.class.getDeclaredFields()) {
                if (field.get(versionAgg) == null) {
                    throw new CreateFailedException(
                            "Field " + field.getName() + " is null in VersionAggregation creation.");
                }
            }
        } catch (IllegalAccessException e) {
            throw new CreateFailedException("Failed to access field during VersionAggregation creation.", e);
        }
        return versionAgg;
    }

    public static VersionAggregation createNew(String productContentId) throws CreateFailedException {
        if (productContentId == null) {
            throw new CreateFailedException("versionId cannot be null.");
        }

        VersionAggregation versionAggregation = new VersionAggregation();
        versionAggregation.id = productContentId;
        versionAggregation.createTime = new Date(); // 设置createTime为当前时间

        // 设置其他属性为默认值
        versionAggregation.name = "v1";
        versionAggregation.detail = "";

        return versionAggregation;
    }

}
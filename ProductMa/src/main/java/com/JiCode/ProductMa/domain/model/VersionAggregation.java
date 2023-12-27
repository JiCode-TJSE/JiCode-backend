package com.JiCode.ProductMa.domain.model;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.domain.model.entity.requirement.BacklogItemsEntity;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Getter;

@Getter
public class VersionAggregation {

    private String versionId;

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

    public static <T> VersionAggregation createVersionByAll(T template)
            throws CreateFailedException {
        VersionAggregation versionAgg = new VersionAggregation();
        BeanUtils.copyProperties(template, versionAgg);
        try {
            for (Field field : BacklogItemsEntity.class.getDeclaredFields()) {
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
        versionAggregation.versionId = productContentId;
        versionAggregation.createTime = new Date(); // 设置createTime为当前时间

        // 设置其他属性为默认值
        versionAggregation.name = "v1";
        versionAggregation.detail = "";

        return versionAggregation;
    }

}

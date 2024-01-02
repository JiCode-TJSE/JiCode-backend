package com.JiCode.ProductMa.domain.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

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
        BeanUtils.copyProperties(this, template);
    }

    public <T> void update(T template) throws CopyFailedException {
        String id = this.id;
        Date createTime = this.createTime;
        BeanUtils.copyProperties(template, this);
        if (!this.id.equals(id) || !this.createTime.equals(createTime)) {
            throw new CopyFailedException("id or createTime cannot be changed.");
        }
        this.dirty = true;
    }

    public static <T> VersionAggregation createVersionByAll(T template)
            throws CreateFailedException {
        VersionAggregation versionAgg = new VersionAggregation();
        BeanUtils.copyProperties(template, versionAgg);
        // TODO 这边记得之后还得加一下错误检测
        versionAgg.cleanDirty();
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

    public static <T> VersionAggregation create(T template) throws CreateFailedException {
        VersionAggregation versionAgg = new VersionAggregation();
        BeanUtils.copyProperties(template, versionAgg);
        versionAgg.cleanDirty();
        versionAgg.createTime = new Date(); // 设置createTime为当前时间
        versionAgg.id = UUID.randomUUID().toString();
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

    public static <T> VersionAggregation[] createByArr(T[] arr) {
        VersionAggregation[] versionAggregations = new VersionAggregation[arr.length];
        // 处理传入的数组
        for (int i = 0; i < arr.length; i++) {
            T item = arr[i];
            VersionAggregation versionAggregation = new VersionAggregation();
            BeanUtils.copyProperties(item, versionAggregation);
            versionAggregation.cleanDirty();
            versionAggregations[i] = versionAggregation;
        }

        return versionAggregations;
    }

}

package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class VersionsEntity {

    private VersionAggregation[] versionArr;

    private boolean dirty;

    public boolean isDirty() {
        return dirty;
    }

    public void cleanDirty() {
        this.dirty = false;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    private VersionsEntity() {
    }

    public static VersionsEntity createByAll(VersionAggregation[] aggs) throws CreateFailedException {
        if (aggs == null) {
            throw new CreateFailedException("VersionAggregation cannot be null.");
        }
        VersionsEntity versionsEntity = new VersionsEntity();
        versionsEntity.versionArr = aggs;
        return versionsEntity;
    }

    public static <T> VersionsEntity create(T template) throws CreateFailedException {
        VersionsEntity versionsEntity = new VersionsEntity();
        BeanUtils.copyProperties(template, versionsEntity);
        try {
            for (Field field : VersionsEntity.class.getDeclaredFields()) {
                if (field.get(versionsEntity) == null) {
                    throw new CreateFailedException(
                            "Field " + field.getName() + " is null in VersionsEntity creation.");
                }
            }
        } catch (IllegalAccessException e) {
            throw new CreateFailedException("Failed to access field during VersionsEntity creation.", e);
        }
        return versionsEntity;
    }

    public static VersionsEntity createNew(VersionAggregation v1) throws CreateFailedException {
        if (v1 == null) {
            throw new CreateFailedException("VersionAggregation cannot be null.");
        }

        VersionsEntity versionsEntity = new VersionsEntity();
        versionsEntity.versionArr = new VersionAggregation[1];
        versionsEntity.versionArr[0] = v1;
        return versionsEntity;
    }

}
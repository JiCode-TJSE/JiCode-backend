package com.JiCode.ProductMa.domain.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.domain.model.entity.requirement.BacklogItemsEntity;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Getter;

@Getter
public class VersionAggregation {

    private String id;

    private String name;

    private String description;

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

}

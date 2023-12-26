package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CopyFailedException;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Getter;

@Getter
public class RequirementContentEntity {

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

    public <T> void copyProperties(T template)
            throws CopyFailedException {
        RequirementContentEntity requirementContentEntity = new RequirementContentEntity();
        BeanUtils.copyProperties(template, requirementContentEntity);
        for (Field field : RequirementContentEntity.class.getDeclaredFields()) {
            try {
                if (field.get(requirementContentEntity) == null) {
                    throw new CopyFailedException("Failed to copy property. Field " + field.getName() + " is null.");
                }
            } catch (IllegalAccessException e) {
                throw new CopyFailedException("Failed to access field " + field.getName() + ".", e);
            }
        }
        this.dirty = true;
    }

    private RequirementContentEntity() {
    }

    public static <T> RequirementContentEntity create(T template)
            throws CreateFailedException {
        RequirementContentEntity requirementContentEntity = new RequirementContentEntity();
        try {
            BeanUtils.copyProperties(template, requirementContentEntity);
            for (Field field : RequirementContentEntity.class.getDeclaredFields()) {
                if (field.get(requirementContentEntity) == null) {
                    throw new CreateFailedException("Field " + field.getName() + " is null");
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new CreateFailedException("Failed to create RequirementContentEntity. " + e.getMessage(), e);
        }
        return requirementContentEntity;
    }

}
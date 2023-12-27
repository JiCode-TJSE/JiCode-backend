package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.JiCode.ProductMa.exception.CopyFailedException;
import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class RequirementContentEntity {

    private String name;

    private String detail;

    private String supervisorId;

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

    public <T> void copyPropertiesTo(T template) throws CopyFailedException {
        try {
            BeanUtils.copyProperties(this, template);
        } catch (BeansException e) {
            throw new CopyFailedException("Failed to copy properties to template.", e);
        }
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

    public static <T> RequirementContentEntity createNew(T template)
            throws CreateFailedException {
        RequirementContentEntity requirementContentEntity = new RequirementContentEntity();
        BeanUtils.copyProperties(template, requirementContentEntity);

        if (requirementContentEntity.getName() == null || requirementContentEntity.getName().trim().isEmpty()) {
            throw new CreateFailedException("Name is required.");
        }

        return requirementContentEntity;
    }

}
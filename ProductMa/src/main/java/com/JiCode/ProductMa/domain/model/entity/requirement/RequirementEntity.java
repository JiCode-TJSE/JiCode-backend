package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Getter;

@Getter
public class RequirementEntity {

    private String id;

    private String inVersion;

    private String belongProductID;

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

    public void setInVersion(String versionId) {
        this.inVersion = versionId;
        this.dirty = true;
    }

    private RequirementEntity() {
    }

    public static <T> RequirementEntity create(T template) throws CreateFailedException {
        RequirementEntity requirementEntity = new RequirementEntity();
        BeanUtils.copyProperties(template, requirementEntity);
        try {
            for (Field field : RequirementEntity.class.getDeclaredFields()) {
                if (field.get(requirementEntity) == null) {
                    throw new CreateFailedException(
                            "Field " + field.getName() + " is null in RequirementEntity creation.");
                }
            }
        } catch (IllegalAccessException e) {
            throw new CreateFailedException("Failed to access field during RequirementEntity creation.", e);
        }
        return requirementEntity;
    }

}
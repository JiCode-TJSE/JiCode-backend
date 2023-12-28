package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class RequirementEntity {

    private String requirementId;

    private String requirementContentId;

    private String belongProductId;

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

    public void setRequirementContentId(String requirementContentId) {
        this.requirementContentId = requirementContentId;
        this.dirty = true;
    }

    public void setId(String requirementId) {
        this.requirementId = requirementId;
        this.dirty = true;
    }

    private RequirementEntity() {
    }

    public static <T> RequirementEntity create(T template) throws CreateFailedException {
        RequirementEntity requirementEntity = new RequirementEntity();
        BeanUtils.copyProperties(template, requirementEntity);
        requirementEntity.cleanDirty();
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

    public static RequirementEntity createNew(String requirementId, String requirementContentId, String belongProductId)
            throws CreateFailedException {
        if (requirementId == null || requirementContentId == null || belongProductId == null) {
            throw new CreateFailedException("Parameters cannot be null.");
        }

        RequirementEntity requirementEntity = new RequirementEntity();
        requirementEntity.requirementId = requirementId;
        requirementEntity.requirementContentId = requirementContentId;
        requirementEntity.belongProductId = belongProductId;

        return requirementEntity;
    }

}
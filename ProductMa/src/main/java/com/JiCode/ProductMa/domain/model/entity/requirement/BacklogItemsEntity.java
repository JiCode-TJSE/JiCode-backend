package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class BacklogItemsEntity {

    private String[] backlogItemIDArr;

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

    public void setBacklogItemIDArr(String[] backlogItemIDArr) {
        this.backlogItemIDArr = backlogItemIDArr;
        this.dirty = true;
    }

    public static BacklogItemsEntity createByAll(String[] backlogItemIDArr) throws CreateFailedException {
        if (backlogItemIDArr == null) {
            throw new CreateFailedException("backlogItemIDArr cannot be null.");
        }
        BacklogItemsEntity backlogItemsEntity = new BacklogItemsEntity();
        backlogItemsEntity.backlogItemIDArr = backlogItemIDArr;
        return backlogItemsEntity;
    }

    private BacklogItemsEntity() {
    }

    public static <T> BacklogItemsEntity create(T template)
            throws CreateFailedException {
        BacklogItemsEntity backlogItemsEntity = new BacklogItemsEntity();
        try {
            BeanUtils.copyProperties(template, backlogItemsEntity);
            for (Field field : BacklogItemsEntity.class.getDeclaredFields()) {
                if (field.get(backlogItemsEntity) == null) {
                    throw new IllegalArgumentException("Field " + field.getName() + " is null");
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new CreateFailedException("Failed to create BacklogItemsEntity. " + e.getMessage(), e);
        }
        return backlogItemsEntity;
    }

    public static BacklogItemsEntity createNew() {
        BacklogItemsEntity backlogItemsEntity = new BacklogItemsEntity();
        backlogItemsEntity.backlogItemIDArr = new String[0];
        return backlogItemsEntity;
    }

}

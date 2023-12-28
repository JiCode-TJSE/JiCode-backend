package com.JiCode.ProductMa.domain.model.entity.requirement;

import java.lang.reflect.Field;

import org.springframework.beans.BeanUtils;

import com.JiCode.ProductMa.exception.CreateFailedException;

import lombok.Data;

@Data
public class ClientsEntity {

    private String[] clientIDArr;

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

    public void setClientIDArr(String[] clientIDArr) {
        this.clientIDArr = clientIDArr;
        this.dirty = true;
    }

    private ClientsEntity() {
    }

    public static ClientsEntity createByAll(String[] clientIDArr) throws CreateFailedException {
        if (clientIDArr == null) {
            throw new CreateFailedException("clientIDArr cannot be null.");
        }
        ClientsEntity clientsEntity = new ClientsEntity();
        clientsEntity.clientIDArr = clientIDArr;
        return clientsEntity;
    }

    public static <T> ClientsEntity create(T template)
            throws CreateFailedException {
        ClientsEntity clientsEntity = new ClientsEntity();
        try {
            BeanUtils.copyProperties(template, clientsEntity);
            for (Field field : ClientsEntity.class.getDeclaredFields()) {
                if (field.get(clientsEntity) == null) {
                    throw new IllegalArgumentException("Field " + field.getName() + " is null");
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new CreateFailedException("Failed to create ClientsEntity. " + e.getMessage(), e);
        }
        return clientsEntity;
    }

    public static ClientsEntity createNew() {
        ClientsEntity clientsEntity = new ClientsEntity();
        clientsEntity.clientIDArr = new String[0];
        return clientsEntity;
    }

}
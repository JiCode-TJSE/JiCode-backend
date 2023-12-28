package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.VersionsEntity;
import com.JiCode.ProductMa.exception.CreateFailedException;
import com.JiCode.ProductMa.exception.requirement.SwitchVersionException;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementContentEntity;
import com.JiCode.ProductMa.domain.model.entity.requirement.ClientsEntity;

import java.util.UUID;

import com.JiCode.ProductMa.domain.model.entity.requirement.BacklogItemsEntity;

import lombok.Data;

@Data
public class RequirementAggregation {

    private RequirementEntity requirementEntity;

    private VersionsEntity versionsEntity;

    private RequirementContentEntity requirementContentEntity;

    private ClientsEntity clientsEntity;

    private BacklogItemsEntity backlogItemsEntity;

    /**
     * @Description 清除所有脏标记
     */
    public void cleanDirty() {
        requirementEntity.cleanDirty();
        versionsEntity.cleanDirty();
        requirementContentEntity.cleanDirty();
        clientsEntity.cleanDirty();
        backlogItemsEntity.cleanDirty();
    }

    public boolean isRequirementDirty() {
        return requirementEntity.isDirty();
    }

    public boolean isClientsDirty() {
        return clientsEntity.isDirty();
    }

    public boolean isBacklogItemsDirty() {
        return backlogItemsEntity.isDirty();
    }

    public boolean isVersionsDirty() {
        return versionsEntity.isDirty();
    }

    public boolean isRequirementContentDirty() {
        return requirementContentEntity.isDirty();
    }

    public void switchVersion(String versionId) throws SwitchVersionException {
        // 检查 versionId 是否在现在的 versionArr 中，这边领域层要维护自己的数据一致性，所以要在这里检查
        boolean versionIdExists = false;
        for (VersionAggregation versionAggregation : versionsEntity.getVersionArr()) {
            if (versionAggregation.getId().equals(versionId)) {
                versionIdExists = true;
                break;
            }
        }

        if (!versionIdExists) {
            throw new SwitchVersionException("VersionId " + versionId + " is not in the current versionArr.");
        }
        this.requirementEntity.setRequirementContentId(versionId);
    }

    public void update(
            RequirementContentEntity requirementContentEntity,
            ClientsEntity clientsEntity,
            BacklogItemsEntity backlogItemsEntity) {
        this.requirementContentEntity.update(requirementContentEntity);
        this.clientsEntity.update(clientsEntity);
        this.backlogItemsEntity.update(backlogItemsEntity);
    }

    private RequirementAggregation() {
    }

    public static RequirementAggregation createRequirementByAll(
            RequirementEntity requirementEntity,
            VersionsEntity versionsEntity,
            RequirementContentEntity requirementContentEntity,
            ClientsEntity clientsEntity,
            BacklogItemsEntity backlogItemsEntity) throws CreateFailedException {
        if (requirementEntity == null || versionsEntity == null || requirementContentEntity == null
                || clientsEntity == null || backlogItemsEntity == null) {
            throw new CreateFailedException(
                    "Params must not be null in creating RequirementAggregation createRequirementByAll().");
        }
        RequirementAggregation agg = new RequirementAggregation();
        agg.backlogItemsEntity = backlogItemsEntity;
        agg.clientsEntity = clientsEntity;
        agg.requirementContentEntity = requirementContentEntity;
        agg.requirementEntity = requirementEntity;
        agg.versionsEntity = versionsEntity;
        return agg;
    }

    public static RequirementAggregation createNew(
            String belongProductId, RequirementContentEntity requirementContentEntity) throws CreateFailedException {
        if (requirementContentEntity == null || belongProductId == null) {
            throw new CreateFailedException(
                    "Params must not be null in creating RequirementAggregation createRequirementByRequirement().");
        }
        String requirementId = UUID.randomUUID().toString();
        String requirementContentId = UUID.randomUUID().toString();
        RequirementEntity requirementEntity = RequirementEntity.createNew(requirementId, requirementContentId,
                belongProductId);
        VersionAggregation versionAggregation = VersionAggregation.createNew(requirementContentId);
        VersionsEntity versionsEntity = VersionsEntity.createNew(versionAggregation);
        ClientsEntity clientsEntity = ClientsEntity.createNew();
        BacklogItemsEntity backlogItemsEntity = BacklogItemsEntity.createNew();
        return RequirementAggregation.createRequirementByAll(requirementEntity, versionsEntity,
                requirementContentEntity, clientsEntity, backlogItemsEntity);
    }

    public static RequirementAggregation createRequirementByOnlyRV(
            RequirementEntity requirementEntity, VersionsEntity versionsEntity) throws CreateFailedException {
        if (requirementEntity == null || versionsEntity == null) {
            throw new CreateFailedException(
                    "Params must not be null in creating RequirementAggregation createRequirementByOnlyRV().");
        }
        RequirementAggregation agg = new RequirementAggregation();
        agg.requirementEntity = requirementEntity;
        agg.versionsEntity = versionsEntity;
        return agg;
    }
}

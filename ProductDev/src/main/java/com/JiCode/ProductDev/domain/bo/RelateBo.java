package com.JiCode.ProductDev.domain.bo;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class RelateBo {
    public String id;
    public RelateItemTypeEnum type;

    public RelateBo(String id, RelateItemTypeEnum type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RelateItemTypeEnum getType() {
        return type;
    }

    public void setType(RelateItemTypeEnum type) {
        this.type = type;
    }
}

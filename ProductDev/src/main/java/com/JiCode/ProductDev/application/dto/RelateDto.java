package com.JiCode.ProductDev.application.dto;

import com.JiCode.ProductDev.common.RelateItemTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelateDto {
    String id1;
    RelateItemTypeEnum type1;
    String id2;
    RelateItemTypeEnum type2;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public RelateItemTypeEnum getType1() {
        return type1;
    }

    public void setType1(RelateItemTypeEnum type1) {
        this.type1 = type1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public RelateItemTypeEnum getType2() {
        return type2;
    }

    public void setType2(RelateItemTypeEnum type2) {
        this.type2 = type2;
    }
}

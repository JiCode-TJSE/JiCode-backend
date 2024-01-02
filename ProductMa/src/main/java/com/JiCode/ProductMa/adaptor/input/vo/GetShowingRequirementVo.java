package com.JiCode.ProductMa.adaptor.input.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GetShowingRequirementVo {
    public List<ShowingData> showingDatas;

    public GetShowingRequirementVo() {
        this.showingDatas = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    static public class ShowingData {
        public String product_name;
        public String type;
        public String item_name;
    }
}
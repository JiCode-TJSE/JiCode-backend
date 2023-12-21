package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

public class ProductMemberKey {
    private String memberId;

    private String productId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }
}
package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.repository.ProductRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@NoArgsConstructor
public class ProductAggregation {

    @Autowired
    ProductRepository productRepository;

    //product表
    private String id;
    private String title;
    private String detail;
    private Boolean visibility;
    private String mark;
    private String teamId;//所属组织

    //requirement表
    private List<String> requirementList;

    //client表
    private List<String> clientList;

    //product_member表
    private List<String> memberList;

    static public ProductAggregation createProduct(
            String id,
            String title,
            String detail,
            Boolean visibility,
            String mark,
            String teamId,
            List<String> clientList,
            List<String> requirementList,
            List<String> memberList
    ){
        ProductAggregation productAggregation = new ProductAggregation();
        productAggregation.id = id;
        productAggregation.title = title;
        productAggregation.detail = detail;
        productAggregation.visibility = visibility;
        productAggregation.mark = mark;
        productAggregation.teamId = teamId;
        productAggregation.clientList = clientList;
        productAggregation.requirementList = requirementList;
        productAggregation.memberList = memberList;
        return productAggregation;

    }

}

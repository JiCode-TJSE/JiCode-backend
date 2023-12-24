package com.JiCode.ProductMa.domain.model;

import com.JiCode.ProductMa.domain.repository.ClientRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class ClientAggregation {

    @Autowired
    ClientRepository clientRepository;

    //client表
    private String id;
    private String rank;
    private String size;
    private String name;
    private String type;
    private String detail;

    //product表
    private String productId;

    public String getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public String getProductId() {
        return productId;
    }

    /**
     *  工程模式
     * @param id
     * @param rank
     * @param size
     * @param name
     * @param type
     * @param detail
     * @param productId
     * @return
     */
    static public ClientAggregation createClient(
            String id,
            String rank,
            String size,
            String name,
            String type,
            String detail,
            String productId
    ){
        ClientAggregation clientAggregation = new ClientAggregation();
        clientAggregation.id = id;
        clientAggregation.rank = rank;
        clientAggregation.size = size;
        clientAggregation.name = name;
        clientAggregation.type = type;
        clientAggregation.detail = detail;
        clientAggregation.productId = productId;
        System.out.println(clientAggregation);
        return clientAggregation;
    }

}

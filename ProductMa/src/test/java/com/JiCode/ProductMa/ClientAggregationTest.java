package com.JiCode.ProductMa;


import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductMaApplication.class)
public class ClientAggregationTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testSelectById() throws NotFoundException {
        System.out.println(clientRepository==null);
        ClientAggregation clientAggregation = this.clientRepository.selectById("test_client");
        System.out.println(clientAggregation);
    }

//    @Test
//    public void testInsert() throws InsertFailedException {
//        String id = String.valueOf(UUID.randomUUID());
//        String rank = "高级";
//        String size = "小规模";
//        String name = "客户1";
//        String type = "类型1";
//        String detail = "这是客户1";
//        String productId = String.valueOf(UUID.randomUUID());
//        ClientAggregation clientAggregation = ClientAggregation.createClient(id, rank, size, name, type, detail, productId);
//        clientRepository.insert(clientAggregation);
//    }

//    @Test
//    public void testUpdateClientById() throws UpdateFailedException {
//        String id = String.valueOf(UUID.randomUUID());
//        String rank = "update1";
//        String size = "update1";
//        String name = "update1";
//        String type = "update1";
//        String detail = "这是客户1";
//        String productId = String.valueOf(UUID.randomUUID());
//        ClientAggregation clientAggregation = ClientAggregation.createClient(id, rank, size, name, type, detail, productId);
//        clientRepository.update(clientAggregation);
//    }

//    @Test
//    public void testDeleteById(){
//        String id = "";
//        clientRepository.delete(id);
//    }

}

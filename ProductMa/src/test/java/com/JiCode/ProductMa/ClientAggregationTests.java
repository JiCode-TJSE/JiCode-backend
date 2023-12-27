package com.JiCode.ProductMa;


import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.NotFoundException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductMaApplication.class)
public class ClientAggregationTests {
    @Autowired
    ClientRepository clientRepository;

//    @Test
//    public void testSelectById() throws NotFoundException {
//        System.out.println(clientRepository==null);
//        ClientAggregation clientAggregation = this.clientRepository.selectById("test_client");
//        System.out.println(clientAggregation);
//    }

    //test ok
//    @Test
//    public void testInsert() throws InsertFailedException {
//        String id = String.valueOf(UUID.randomUUID());
//        String rank = "client_test";
//        String size = "client_test";
//        String name = "client_test";
//        String type = "client_test";
//        String detail = "client_test";
//        String productId = "test";
//        ClientAggregation clientAggregation = ClientAggregation.createClient(id, rank, size, name, type, detail, productId);
//        clientRepository.insert(clientAggregation);
//    }

    //test ok
//    @Test
//    public void testUpdateClientById() throws UpdateFailedException {
//        String id = "test_client";
//        String rank = "update1";
//        String size = "update1";
//        String name = "update1";
//        String type = "update1";
//        String detail = "这是客户1";
//        String productId = "test";
//        ClientAggregation clientAggregation = ClientAggregation.createClient(id, rank, size, name, type, detail, productId);
//        clientRepository.update(clientAggregation);
//    }

//    //test ok
//    @Test
//    public void testDeleteById() throws DeleteFailedException {
//        String id = "e0922711-03e2-432a-88c6-2136ffda4758";
//        clientRepository.delete(id);
//    }
}

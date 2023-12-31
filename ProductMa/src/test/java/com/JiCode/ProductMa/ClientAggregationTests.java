package com.JiCode.ProductMa;


import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
//        //实际调用时id应该为""
//        String id = "";
//        String rank = "美羊羊";
//        String size = "美洋洋";
//        String name = "小样";
//        String type = "小羊";
//        String detail = "羊";
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


//    //test ok
//    @Test
//    public void testSelectClientByPage() throws SelectFailedException {
//        List<ClientAggregation> clientAggregations = clientRepository.selectByPage("test", 1, 20);
//        System.out.println(clientAggregations);
//    }

    //test ok
//    @Test
//    public void testSelectNamesByIds() throws SelectFailedException {
//        String[] clientIds = {"test_client", "9e731863-6cc9-4a87-bc5f-bbacf37019c6", "e1d42f8a-abd0-45b5-af09-b13486017bff"};
//        String[] clientNames;
//        try {
//            clientNames = clientRepository.selectNamesById(clientIds);
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        for(int i = 0; i < clientIds.length; i++){
//            System.out.println(clientNames[i]);
//        }
//    }


//    //test ok
//    @Test
//    public void testSelectByNames() throws SelectFailedException, NotFoundException {
//        String keyword = "小样";
//        List<ClientAggregation> clientAggregations = clientRepository.selectByClientName(keyword);
//
//        for(ClientAggregation clientAggregation : clientAggregations){
//            System.out.println(clientAggregation.getName());
//        }
//    }

}

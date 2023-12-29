package com.JiCode.ProductMa;

import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.domain.repository.ProductRepository;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.NotFoundException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductMaApplication.class)
public class ProductAggregationTests {
    @Autowired
    ProductRepository productRepository;


    //test ok
//    @Test
//    public void testSelectById() throws Exception {
//        ProductAggregation productAggregation = this.productRepository.selectById("test");
//        System.out.println(productAggregation);
//    }

//    //test ok
//    @Test
//    public void testInsert() throws Exception {
//        String id = String.valueOf(UUID.randomUUID());
//        String title = "product_test";
//        String detail = "product_test";
//        Boolean visibility = Boolean.TRUE;
//        String mark = "product_test";
//        String teamId = "test_team";
//        //不能为空
//        List<String> members = new ArrayList<>();
//        ProductAggregation productAggregation = ProductAggregation.createProduct(id, title, detail, visibility, mark, teamId, members);
//        productRepository.insert(productAggregation);
//    }


    //test ok
//    @Test
//    public void testUpdateProductById() throws Exception {
//        String id = "test";
//        String title = "update1";
//        String detail = "update2";
//        Boolean visibility = Boolean.TRUE;
//        String mark = "product_test";
//        String teamId = "test_team";
//        List<String> members = new ArrayList<String>();
//        ProductAggregation productAggregation = ProductAggregation.createProduct(id, title, detail, visibility, mark, teamId, members);
//        productRepository.update(productAggregation);
//    }


    //test ok
//    @Test
//    public void testDeleteById() throws Exception {
//        String id = "1e280629-cc71-430e-8f6e-31e4ae9937b8";
//        productRepository.delete(id);
//    }


}

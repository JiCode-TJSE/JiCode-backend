package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.*;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ProductMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ProductMemberMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementMapper;
import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.JiCode.ProductMa.domain.repository.ProductRepository;
import com.JiCode.ProductMa.exception.product.repository.DeleteProductFailedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ProductMemberMapper productMemberMapper;

    @Autowired
    RequirementMapper requirementMapper;


    @Override
    public ProductAggregation selectById(String id) {
        //product 实体查询
        Product product = selectProduct(id);
        List<String> clientList = selectClientIds(product);
        List<String> requirementList = selectRequirementIds(product);
        List<String> memberList = selectMemberIds(product);

        //返回聚合
        return ProductAggregation.createProduct(
                product.getId(),
                product.getTitle(),
                product.getDetail(),
                product.getVisibility(),
                product.getMark(),
                product.getTeamId(),
                clientList,
                requirementList,
                memberList
        );


        //条件查询：client
            //创建查询条件：查实体集Client
        ClientExample clientExample = new ClientExample();
        clientExample.createCriteria().andProductIdEqualTo(id);
        List<Client> clients = clientMapper.selectByExample(clientExample);
            //提取数组：client
        List<String> clientIds= clients.stream()
                .map(Client::getId)
                .collect(Collectors.toList());//collect()将Stream元素手机到List

        //条件查询：requirement
            //创建查询条件：查实体集Requirement
        RequirementExample requirementExample = new RequirementExample();
        requirementExample.createCriteria().andBelongProductIdEqualTo(id);
        List<Requirement> requirements = requirementMapper.selectByExample(requirementExample);
            //提取数组
        List<String> requirementIds = requirements.stream()
                .map(Requirement::getId)
                .collect(Collectors.toList());

        //条件查询：members
            //创建查询条件：查联系集ProductMemberKey
        ProductMemberExample pmExample = new ProductMemberExample();
        pmExample.createCriteria().andProductIdEqualTo(id);
        List<ProductMemberKey> pmKeys = productMemberMapper.selectByExample(pmExample);
            //提取数组
        List<String> memberIds = pmKeys.stream()
                .map(ProductMemberKey::getMemberId)
                .collect(Collectors.toList());


    }

    private Product selectProduct(String id) throws ProductNotFoundException{
        Product product = productMapper.selectByPrimaryKey(id);
        if( product == null){
            throw new ProductNoFoundException("Product not found.");
        }
        return product;
    }

    private

    @Override
    public void insert(ProductAggregation productAggregation) {
        try{
            //插入product
            Product product = new Product();
            BeanUtils.copyProperties(productAggregation, product);
            product.setId(UUID.randomUUID().toString());

            //插入client
            for (String clientId : productAggregation.getClientList()){
                Client client = new Client();

            }

            //插入requirement

            //插入members



            return productMapper.insert(product);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void update(ProductAggregation productAggregation) {
        try{
            //对实体集操作
            Product product = new Product();
            BeanUtils.copyProperties(productAggregation, product);
            //UUID生成ID
            product.setId(UUID.randomUUID().toString());
            return productMapper.insert(product); //插入product表
            //TODO:需要对聚合涉及的联系集插入数据吗？

        }
    }

    @Override
    public void delete(String id) throws DeleteProductFailedException {
        int result = productMapper.deleteByPrimaryKey(id);
        if (result <= 0){
            throw new DeleteProductFailedException("Delete product failed.");
        }

    }
}

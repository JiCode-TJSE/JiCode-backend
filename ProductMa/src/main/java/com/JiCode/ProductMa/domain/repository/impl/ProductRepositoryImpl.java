package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Product;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ProductMemberExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ProductMemberKey;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ProductMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ProductMemberMapper;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.RequirementMapper;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.domain.repository.ProductRepository;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
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

    //调用聚合根Client的仓储
    private final ClientRepository clientRepository;

    //调用聚合根Requirement的仓储
    private final RequirementRepository requirementRepository;

    public ProductRepositoryImpl(ClientRepository clientRepository, RequirementRepository requirementRepository) {
        this.clientRepository = clientRepository;
        this.requirementRepository = requirementRepository;
    }

    //按ID查询Product
    @Override
    public ProductAggregation selectById(String id) throws Exception {
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
    }

    private Product selectProduct(String id) throws Exception{
        Product product = productMapper.selectByPrimaryKey(id);
        if( product == null){
            throw new Exception("Select ProductAgg: product not found.");
        }
        return product;
    }

    //调用Client仓储按ProductId查询
    private List<String> selectClientIds(Product product) throws Exception{
        List<ClientAggregation> clientAggregations = clientRepository.selectByProductId(product.getId());
        return clientAggregations.stream().map(ClientAggregation::getId).collect(Collectors.toList());
    }

    //查product_member联系集
    private List<String> selectMemberIds(Product product) throws Exception{
        ProductMemberExample example = new ProductMemberExample();
        ProductMemberExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(product.getId());
        List<ProductMemberKey> keys = productMemberMapper.selectByExample(example);
        if (keys == null || keys.isEmpty()){
            throw new Exception("Select ProductAgg:member not found.");
        }

        //提取数组：clientIds
        return keys.stream()
                .map(ProductMemberKey::getMemberId)
                .collect(Collectors.toList());//collect()将Stream元素手机到List
    }

    //调用Requirement按ProductId查
    private List<String> selectRequirementIds(Product product) throws Exception{
//        // TODO:RequirementRepository增加一个selectByProductId的操作？
//        List<RequirementAggregation> requirementAggregations = requirementRepository.selectById(product.getId());
//        return requirementAggregations.stream()
//                .map(RequirementAggregation::getId)
//                .collect(Collectors.toList());
    }


    //插入Product
    @Override
    public void insert(ProductAggregation productAggregation) throws Exception{
        Product product = insertProduct(productAggregation);
//        //TODO: 插入product的时候可能还没有clients、requirements、members?
//        //什么时候insertClients，还是另外调用，还是在domain service里？
//        insertClients(productAggregation, product);
//        insertRequirement(productAggregation, product);
//        insertMembers(productAggregation, product);
    }

    //插入product_member表
    private void insertMembers(ProductAggregation productAggregation, Product product) throws Exception {
        for(String memberId : productAggregation.getMemberList()){
            ProductMemberKey productMemberKey = new ProductMemberKey();
            productMemberKey.setProductId(product.getId());
            productMemberKey.setMemberId(memberId);
            int memberResult = productMemberMapper.insert(productMemberKey);
            if(memberResult <= 0){
                throw new Exception("Insert ProductAggregation: insert member failed.");
            }
        }
    }

    //调用RequirementAggregation仓储插入
    private void insertRequirement(ProductAggregation productAggregation, Product product) {
        //TODO: 调用RequirementRepository
    }

    //调用ClientAggregation仓储插入
    private void insertClients(ProductAggregation productAggregation, Product product) throws Exception{
        // 获取待插入的clientId列表
    }

    //插入product表
    private Product insertProduct(ProductAggregation productAggregation) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productAggregation, product);
        //设置唯一标识符
        product.setId(UUID.randomUUID().toString());
        int productResult = productMapper.insert(product);
        if(productResult <= 0){
            throw new Exception("Insert ProductAggregation: insert product failed.");
        }
        return product;
    }

    @Override
    public void update(ProductAggregation productAggregation) throws Exception{
        //脏标记处理，未改动的则不update表
        //脏数据在哪里检测？应用层吗？
        if (productAggregation.isProductDirty()){
            updateProduct(productAggregation);
        }
        if (productAggregation.isRequirementDirty()){
            updateRequirement(productAggregation);
        }
        if (productAggregation.isClientDirty()){
            updateClient(productAggregation);
        }
        if (productAggregation.isMemberDirty()){
            updateProductMember(productAggregation);
        }
        productAggregation.cleanDirty();
    }

    private void updateProduct(ProductAggregation productAggregation) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productAggregation, product);
        int productResult = productMapper.updateByPrimaryKey(product);
        if (productResult <= 0){
            throw new Exception("Update product failed.");
        }
    }

    private void updateRequirement(ProductAggregation productAggregation) {
        //TODO: 调用Requirement的接口？
    }

    private void updateClient(ProductAggregation productAggregation) {
        //TODO: 调用Client的接口
    }

    private void updateProductMember(ProductAggregation productAggregation) {
        //TODO: 先找对应ProductId的ClientId，然后删掉原来的插入新的？
        ProductMemberExample example = new ProductMemberExample();
//        example.createCriteria().andProductIdEqualTo()
    }


    @Override
    public void delete(String id) throws DeleteProductFailedException {
        int result = productMapper.deleteByPrimaryKey(id);
        if (result <= 0){
            throw new DeleteProductFailedException("Delete product failed.");
        }

    }
}

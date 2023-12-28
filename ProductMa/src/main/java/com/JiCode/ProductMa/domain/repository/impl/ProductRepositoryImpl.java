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
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.domain.repository.ProductRepository;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
import com.JiCode.ProductMa.exception.DeleteFailedException;
import com.JiCode.ProductMa.exception.InsertFailedException;
import com.JiCode.ProductMa.exception.SelectFailedException;
import com.JiCode.ProductMa.exception.UpdateFailedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    // 调用聚合根Client的仓储
    private final ClientRepository clientRepository;


    /**
     * 构造函数
     * @param clientRepository
     * @param requirementRepository
     * @param requirementRepository1
     */
    public ProductRepositoryImpl(ClientRepository clientRepository, RequirementRepository requirementRepository, RequirementRepository requirementRepository1) {
        this.clientRepository = clientRepository;
    }


    /**
     * =================================直接对数据库的表进行操作======================================================
     */


    /**
     * 按ID查找实体表product表
     * @param id
     * @return
     * @throws SelectFailedException
     */
    private Product selectProduct(String id) throws SelectFailedException {
        Product product = productMapper.selectByPrimaryKey(id);
        if( product == null){
            throw new SelectFailedException("Select ProductAgg: select product failed with id.");
        }
        return product;
    }


    /**
     * 调用ClientRepository按productId查找对应的clientList
     * @param product
     * @return
     * @throws Exception
     */
    private List<String> selectClientIds(Product product) throws Exception{
        List<ClientAggregation> clientAggregations = clientRepository.selectByProductId(product.getId());
        return clientAggregations.stream().map(ClientAggregation::getId).collect(Collectors.toList());
    }


    /**
     * 查找联系表product_member
     * @param product
     * @return
     * @throws Exception
     */
    private List<String> selectMemberIds(Product product) throws SelectFailedException{
        ProductMemberExample example = new ProductMemberExample();
        ProductMemberExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(product.getId());
        List<ProductMemberKey> keys = productMemberMapper.selectByExample(example);
        if (keys == null){
            throw new SelectFailedException("Select ProductAgg: select product's memberList failed.");
        }
        //返回结果为空，还没有团队成员
        if (keys.isEmpty()){
            return new ArrayList<>();
        }
        //提取数组：memberIds
        return keys.stream()
                .map(ProductMemberKey::getMemberId)
                .collect(Collectors.toList());//collect()将Stream元素收集到List
    }


    /**
     * 插入联系表product_member
     * @param productAggregation
     * @param product
     * @throws InsertFailedException
     */
    private void insertMembers(ProductAggregation productAggregation, Product product) throws InsertFailedException {
        for(String memberId : productAggregation.getMemberList()){
            ProductMemberKey productMemberKey = new ProductMemberKey();
            productMemberKey.setProductId(product.getId());
            productMemberKey.setMemberId(memberId);
            int memberResult = productMemberMapper.insert(productMemberKey);
            if(memberResult <= 0){
                throw new InsertFailedException("Insert ProductAggregation: failed to insert members with id." + memberId);
            }
        }
    }


    /**
     * 更新实体表product表
     * @param productAggregation
     * @throws UpdateFailedException
     */
    private void updateProduct(ProductAggregation productAggregation) throws UpdateFailedException {
        Product product = new Product();
        BeanUtils.copyProperties(productAggregation, product);
        int productResult = productMapper.updateByPrimaryKey(product);
        if (productResult <= 0){
            throw new UpdateFailedException("Update product: update product db_table failed.");
        }
    }


    /**
     * 更新联系表product_member表
     * @param productAggregation
     * @throws UpdateFailedException
     */
    private void updateProductMember(ProductAggregation productAggregation) throws UpdateFailedException {
        //先删除原联系集
        ProductMemberExample example = new ProductMemberExample();
        example.createCriteria().andProductIdEqualTo(productAggregation.getId());
        productMemberMapper.deleteByExample(example);
        //插入新的联系集
        for (String memberId : productAggregation.getMemberList()){
            ProductMemberKey key = new ProductMemberKey();
            key.setProductId(productAggregation.getId());
            key.setMemberId(memberId);
            int result = productMemberMapper.insert(key);
            if (result <= 0){
                throw new UpdateFailedException(
                        "Update ProductAggregation: failed to update member with id" + memberId
                );
            }
        }

    }



    /**
     * ====================================================以下是对接口的实现========================================================
     */


    /**
     * 插入ProductAggregation
     * @param productAggregation
     * @throws InsertFailedException
     */
    @Override
    public void insert(ProductAggregation productAggregation) throws InsertFailedException {
        //新增Product的时候只对product表进行操作，用到其它聚合的地方，比如Client、Requirement在update中完成
        Product product = new Product();
        if (productAggregation.isProductDirty()){
            BeanUtils.copyProperties(productAggregation, product);
            //设置唯一标识符
            product.setId(UUID.randomUUID().toString());
            int productResult = productMapper.insert(product);
            if(productResult <= 0){
                throw new InsertFailedException("Insert ProductAggregation: insert product failed.");
            }
        }
        if (productAggregation.isMemberDirty()) {
            //插入联系集
            insertMembers(productAggregation, product);
        }
        productAggregation.cleanDirty();
    }


    /**
     * 按ID删除Product
     * @param id
     * @throws DeleteFailedException
     */
    @Override
    public void delete(String id) throws DeleteFailedException {
        int result = productMapper.deleteByPrimaryKey(id);
        if (result <= 0){
            throw new DeleteFailedException("Delete product failed.");
        }
    }


    /**
     * 更新ProductAggregation
     * @param productAggregation
     * @throws UpdateFailedException
     * @throws InsertFailedException
     */
    @Override
    public void update(ProductAggregation productAggregation) throws UpdateFailedException, InsertFailedException {
        // 实体集可以直接Update，联系集需要删掉记录重新生成
        //脏标记处理，未改动的则不update表
        //脏数据在Aggregation中处理业务逻辑的时候设置
        if (productAggregation.isProductDirty()){
            updateProduct(productAggregation);
        }
        if (productAggregation.isMemberDirty()){
            updateProductMember(productAggregation);
        }
        productAggregation.cleanDirty();
    }


    /**
     * 按ID查询Product
     * @param id
     * @return ProductAggregation
     * @throws Exception
     */
    @Override
    public ProductAggregation selectById(String id) throws SelectFailedException {
        // product 实体查询
        Product product = selectProduct(id);
        // product_member 联系查询
        List<String> memberList = selectMemberIds(product);

        // 返回聚合
        return ProductAggregation.createProduct(
                product.getId(),
                product.getTitle(),
                product.getDetail(),
                product.getVisibility(),
                product.getMark(),
                product.getTeamId(),
                memberList);
    }
}

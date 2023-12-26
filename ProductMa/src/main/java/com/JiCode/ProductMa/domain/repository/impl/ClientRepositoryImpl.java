package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    ClientMapper clientMapper;


    /**
     * 按ID查找ClientAggregation
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public ClientAggregation selectById(String id) throws NotFoundException {
        Client client = clientMapper.selectByPrimaryKey(id);
        if(client == null){
            throw new NotFoundException("Select ClientAgg: client not found.");
        }
        return ClientAggregation.createClient(
                client.getId(),
                client.getRank(),
                client.getSize(),
                client.getName(),
                client.getType(),
                client.getDetail(),
                client.getProductId());
    }


    //应该返回List<ClientAggregation>还是List<String> ids？？？
    /**
     * 按ProductID查找ClientAggregation
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public List<ClientAggregation> selectByProductId(String productId) throws SelectFailedException {
        ClientExample example = new ClientExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<Client> clients = clientMapper.selectByExample(example);
        if(clients == null || clients.isEmpty()){
            throw new SelectFailedException("Select ClientAgg by productId: client not found.");
        }
        else{
            //返回结果：将Client转为ClientAggregation
            List<ClientAggregation> result = new ArrayList<>();
            for(Client client : clients){
                ClientAggregation clientAggregation = null;
                try {
                    clientAggregation = selectById(client.getId());
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
                result.add(clientAggregation);//插入结果
            }
            return result;
        }
    }


    /**
     * 分页获取客户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ClientAggregation> getPage(int pageNum, int pageSize) {
            PageHelper.startPage(pageNum, pageSize);
            Page<Client> clients = clientMapper.selectByPaging(null);
            List<ClientAggregation> clientAggregations = new ArrayList<>();
            for(Client client : clients){
                //工厂模式建立聚合
                ClientAggregation clientAggregation = ClientAggregation.createClient(
                        client.getId(),
                        client.getRank(),
                        client.getSize(),
                        client.getName(),
                        client.getType(),
                        client.getDetail(),
                        client.getProductId()
                );
                clientAggregations.add(clientAggregation);
            }
            PageInfo<ClientAggregation> result = new PageInfo<>(clientAggregations);
            return result;
    }


    /**
     * 插入ClientAggregation
     * @param clientAggregation
     * @throws InsertFailedException
     */
    @Override
    public void insert(ClientAggregation clientAggregation) throws InsertFailedException {
        Client client = new Client();
        BeanUtils.copyProperties(clientAggregation, client);
        //生成唯一标识符
        client.setId(UUID.randomUUID().toString());
        int result = clientMapper.insert(client);
        if( result <= 0){
            throw new InsertFailedException("Insert client failed.");
        }
    }


    /**
     * 更新ClientAggregation
     * @param clientAggregation
     * @throws UpdateFailedException
     */
    @Override
    public void update(ClientAggregation clientAggregation) throws UpdateFailedException {
            Client client = new Client();
            BeanUtils.copyProperties(clientAggregation, client);
            //按PK更新数据库中的client
            int result = clientMapper.updateByPrimaryKey(client);
            if (result <= 0){
                throw new UpdateFailedException("Update client failed");
            }

    }


    /**
     * 按ID删除ProductAggregation
     * @param id
     * @throws DeleteFailedException
     */
    @Override
    public void delete(String id) throws DeleteFailedException {
        int result = clientMapper.deleteByPrimaryKey(id);
        if (result <= 0){
            throw new DeleteFailedException("Delete client failed.");
        }
    }
}

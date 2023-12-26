package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.client.repository.ClientNotFoundException;
import com.JiCode.ProductMa.exception.client.repository.DeleteClientFailedException;
import com.JiCode.ProductMa.exception.client.repository.InsertClientFailedException;
import com.JiCode.ProductMa.exception.client.repository.UpdateClientFailedException;
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

    @Override
    public ClientAggregation selectById(String id) throws ClientNotFoundException {
        Client client = clientMapper.selectByPrimaryKey(id);
        if(client == null){
            throw new ClientNotFoundException("Select ClientAgg: client not found.");
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

    @Override
    public List<ClientAggregation> selectByProductId(String productId) throws Exception{
        ClientExample example = new ClientExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<Client> clients = clientMapper.selectByExample(example);
        if(clients == null || clients.isEmpty()){
            //TODO: 这里需要抛出异常吗？还是直接返回null？
            throw new Exception("Select ClientAgg by productId: client not found.");
        }
        else{
            //返回结果：将Client转为ClientAggregation
            List<ClientAggregation> result = new ArrayList<>();
            for(Client client : clients){
                ClientAggregation clientAggregation = selectById(client.getId());
                result.add(clientAggregation);//插入结果
            }
            return result;
        }
    }

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

    @Override
    public void insert(ClientAggregation clientAggregation) throws InsertClientFailedException {
        Client client = new Client();
        BeanUtils.copyProperties(clientAggregation, client);
        //生成唯一标识符
        client.setId(UUID.randomUUID().toString());
        int result = clientMapper.insert(client);
        if( result <= 0){
            throw new InsertClientFailedException("Insert client failed.");
        }
    }

    @Override
    public void update(ClientAggregation clientAggregation) throws UpdateClientFailedException {
            Client client = new Client();
            BeanUtils.copyProperties(clientAggregation, client);
            //按PK更新数据库中的client
            int result = clientMapper.updateByPrimaryKey(client);
            if (result <= 0){
                throw new UpdateClientFailedException("Update client failed");
            }

    }

    @Override
    public void delete(String id) throws DeleteClientFailedException {
        int result = clientMapper.deleteByPrimaryKey(id);
        if (result <= 0){
            throw new DeleteClientFailedException("Delete client failed.");
        }
    }
}

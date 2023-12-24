package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
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
    public ClientAggregation selectById(String id) {
        Client client = clientMapper.selectByPrimaryKey(id);
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
    public PageInfo<ClientAggregation> getPage(int pageNum, int pageSize) {
        try{
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
            return new PageInfo<>(clientAggregations);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int insert(ClientAggregation clientAggregation) {
        try{
            Client client = new Client();
            BeanUtils.copyProperties(clientAggregation, client);
            //生成唯一标识符
            client.setId(UUID.randomUUID().toString());
            return clientMapper.insert(client);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int updateById(ClientAggregation clientAggregation) {
        try{
            Client client = new Client();
            BeanUtils.copyProperties(clientAggregation, client);
            //按PK更新数据库中的client
            return clientMapper.updateByPrimaryKey(client);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try{
            return clientMapper.deleteByPrimaryKey(id);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

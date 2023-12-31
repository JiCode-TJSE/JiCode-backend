package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ClientExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Requirement;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.RequirementExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.ClientMapper;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.model.entity.requirement.RequirementEntity;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
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
    public List<ClientAggregation> selectByProductId(String productId) throws NotFoundException {
        ClientExample example = new ClientExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<Client> clients = clientMapper.selectByExample(example);
        if (clients == null || clients.isEmpty()){
            throw new NotFoundException("Select ClientAgg by productId: product is not found");
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
     * 插入ClientAggregation
     * @param clientAggregation
     * @return 生成的clientId
     * @throws InsertFailedException
     */
    @Override
    public String insert(ClientAggregation clientAggregation) throws InsertFailedException {
        Client client = new Client();
        BeanUtils.copyProperties(clientAggregation, client);
        //生成唯一标识符
        client.setId(UUID.randomUUID().toString());
        int result = clientMapper.insert(client);
        if( result <= 0){
            throw new InsertFailedException("Insert client failed.");
        }
        return client.getId();
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
        System.out.println(result);
        if (result <= 0){
            throw new DeleteFailedException("Delete client failed.");
        }
    }


    /**
     * 按productID分页查询
     * @param productId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws SelectFailedException
     */
    @Override
    public List<ClientAggregation> selectByPage(String productId, int pageNo, int pageSize) throws SelectFailedException {
        ClientExample example = new ClientExample();
        example.createCriteria().andProductIdEqualTo(productId);
        RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
        List<Client> clients = clientMapper.selectByExampleWithRowbounds(example, rowBounds);
        if(clients == null){
            throw new SelectFailedException("Select ClientAggByPage by productId: client not found.");
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
     * 根据clientIds批量查询对应的clientNames
     * @param clientIds
     * @return
     * @throws NotFoundException
     */
    @Override
    public String[] selectNamesById(String[] clientIds) throws NotFoundException {
        String[] clientNames = new String[clientIds.length];
        for (int i = 0; i < clientIds.length; i++){
            Client client = clientMapper.selectByPrimaryKey(clientIds[i]);
            if (client == null){
                throw new NotFoundException("ClientRepository: select client's name failed with id——" + clientIds[i]);
            }
            clientNames[i] = client.getName();
        }
            return clientNames;
    }


    /**
     * 按客户名搜索客户列表
     * @param keyword
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<ClientAggregation> selectByClientName(String keyword, String productId) throws NotFoundException {
        ClientExample example = new ClientExample();
        example.createCriteria().andNameLike("%" + keyword + "%").andProductIdEqualTo(productId);

        List<Client> clients = clientMapper.selectByExample(example);
        if(clients == null){
            throw new NotFoundException("ClientRepository: failed to search clientList by name-keyword");
        }
        else {
            //返回结果：将Client转为ClientAggregation
            List<ClientAggregation> result = new ArrayList<>();
            ClientAggregation clientAggregation = null;
            for (Client client : clients) {
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
}

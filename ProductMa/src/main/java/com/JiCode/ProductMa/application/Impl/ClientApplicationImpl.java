package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.ClientApplication;
import com.JiCode.ProductMa.application.dto.AllClientsDto;
import com.JiCode.ProductMa.application.dto.ClientDto;
import com.JiCode.ProductMa.domain.model.ClientAggregation;
import com.JiCode.ProductMa.domain.repository.ClientRepository;
import com.JiCode.ProductMa.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientApplicationImpl implements ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(RequirementApplicationImpl.class);

    @Autowired
    ClientRepository clientRepository;

    /**
     * 获取客户列表 (test ok)
     * 
     * @param productId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws ServerException
     */
    @Transactional(readOnly = true)
    @Override
    public AllClientsDto getAllClientsByProductId(String productId, int pageNo, int pageSize) throws ServerException {
        List<ClientAggregation> clientAggregations;
        try {
            clientAggregations = clientRepository.selectByProductId(productId);
            // 返回体
            AllClientsDto allClientsDto = new AllClientsDto();
            // 记录
            AllClientsDto.Record[] records = new AllClientsDto.Record[clientAggregations.size()];
            for (int i = 0; i < clientAggregations.size(); i++) {
                AllClientsDto.Record record = new AllClientsDto.Record();
                // 将clientAggregations和record的同名属性赋值
                BeanUtils.copyProperties(clientAggregations.get(i), record);
                records[i] = record;
            }

            // 设置 AllClientsDto 的属性
            allClientsDto.setRecords(records);
            allClientsDto.setSize(pageSize);
            allClientsDto.setRecordNum(records.length);
            allClientsDto.setPages((records.length + pageSize - 1) / pageSize);
            allClientsDto.setCurrent(pageNo);
            allClientsDto.setTotal(records.length);
            return allClientsDto;

        } catch (Exception e) {
            log.error("Server Error", e);
            throw new ServerException(e);
        }
    }

    /**
     * 新建客户
     * 
     * @param clientDto
     * @return
     * @throws ServerException
     */
    @Transactional
    @Override
    public ClientDto createClient(ClientDto clientDto) throws ServerException {
        // 新建聚合
        ClientAggregation clientAggregation = new ClientAggregation();
        // 复制同名属性值
        BeanUtils.copyProperties(clientDto, clientAggregation);
        clientAggregation.setProductId(clientDto.getProduct_id());
        try {
            String clientId = clientRepository.insert(clientAggregation);
            // 新建返回Dto
            ClientDto response = new ClientDto();
            BeanUtils.copyProperties(clientDto, response);
            response.setId(clientId);
            return response;

        } catch (InsertFailedException e) {
            log.error("Server Error", e);
            throw new ServerException(e);
        }

    }

    /**
     * 获取客户详情
     * 
     * @param clientId
     * @return
     * @throws ServerException
     */
    @Transactional(readOnly = true)
    @Override
    public ClientDto getClientDetail(String clientId) throws ServerException {
        try {
            ClientAggregation clientAggregation = clientRepository.selectById(clientId);
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientAggregation, clientDto);
            // 这里ClientDto的product_id和ClientAggregation的productId名不同
            clientDto.setProduct_id(clientAggregation.getProductId());
            return clientDto;
        } catch (NotFoundException e) {
            log.error("Server Error", e);
            throw new ServerException(e);
        }

    }

    /**
     * 删除客户
     * 
     * @param clientId
     * @throws ServerException
     */
    @Transactional
    @Override
    public void deleteClient(String clientId) throws ServerException {
        try {
            // 根据 clientId 删除
            clientRepository.delete(clientId);
        } catch (DeleteFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }

    /**
     * 更新客户信息
     * 
     * @param clientDto
     * @return
     * @throws ServerException
     */
    @Transactional
    @Override
    public ClientDto updateClient(ClientDto clientDto) throws ServerException {
        ClientAggregation clientAggregation = new ClientAggregation();
        // 复制请求参数
        BeanUtils.copyProperties(clientDto, clientAggregation);
        clientAggregation.setProductId(clientDto.getProduct_id());
        try {
            clientRepository.update(clientAggregation);
            return clientDto;
        } catch (UpdateFailedException e) {
            log.error("Server Error", e);
            throw new ServerException(e);
        }

    }

}

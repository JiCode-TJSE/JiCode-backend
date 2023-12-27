package com.JiCode.ProductMa.application;

import com.JiCode.ProductMa.application.dto.*;
import com.JiCode.ProductMa.exception.ServerException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ClientApplication {

    /**
     * 获取某个产品的客户列表
     */
    public AllClientsDto getAllClientsByProductId(String productId, int pageNo, int pageSize)
        throws ServerException;

    /**
     * 新建客户
     */
    public ClientDto createClient(ClientDto clientDto)
        throws ServerException;


    /**
     * 获取客户详情
     */
    public ClientDto getClientDetail(String clientId) throws  ServerException;

    /**
     * 删除客户
     */
    public void deleteClient(String clientId) throws ServerException;


    /**
     * 更新客户信息
     */
    public ClientDto updateClient(ClientDto clientDto) throws ServerException;

}

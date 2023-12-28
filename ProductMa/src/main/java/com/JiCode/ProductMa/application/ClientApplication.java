package com.JiCode.ProductMa.application;

import com.JiCode.ProductMa.application.dto.*;
import com.JiCode.ProductMa.exception.ServerException;


public interface ClientApplication {

    /**
     * 获取某个产品的客户列表 (test ok)
     */
    public AllClientsDto getAllClientsByProductId(String productId, int pageNo, int pageSize)
        throws ServerException;

    /**
     * 新建客户（test ok）
     */
    public ClientDto createClient(ClientDto clientDto)
        throws ServerException;


    /**
     * 获取客户详情(test ok)
     */
    public ClientDto getClientDetail(String clientId) throws  ServerException;



    /**
     * 删除客户（test ok)
     */
    public void deleteClient(String clientId) throws ServerException;


    /**
     * 更新客户信息
     */
    public ClientDto updateClient(ClientDto clientDto) throws ServerException;

}

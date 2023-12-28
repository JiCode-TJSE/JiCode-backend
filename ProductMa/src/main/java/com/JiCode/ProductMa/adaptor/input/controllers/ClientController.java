package com.JiCode.ProductMa.adaptor.input.controllers;


import com.JiCode.ProductMa.adaptor.input.vo.CommonVo;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.application.ClientApplication;
import com.JiCode.ProductMa.application.dto.AllClientsDto;
import com.JiCode.ProductMa.application.dto.ClientDto;
import com.JiCode.ProductMa.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ClientController {
    @Autowired
    ClientApplication clientApplication;

    //分页获取客户信息
    @GetMapping("/clients")
    public CommonVo<AllClientsDto> getAllClients(@RequestParam("productId") String productId,
                                                  @RequestParam("pageNo") int pageNo,
                                                  @RequestParam("pageSize") int pageSize)
        throws ServerException {
        AllClientsDto allClientsDto = clientApplication.getAllClientsByProductId(productId, pageNo, pageSize);
        return CommonVo.create("请求成功", true, allClientsDto);
    }

    //获取客户详情
    @GetMapping("/client")
    public CommonVo<ClientDto> getClientDetail(@RequestParam("id") String clientId)
        throws ServerException{
        ClientDto responseData = clientApplication.getClientDetail(clientId);
        return CommonVo.create("请求成功", true, responseData);
    }

    //新建客户
    @PostMapping("/client")
    public CommonVo<ClientDto> createClient(@RequestBody ClientDto clientDto)
        throws ServerException{
        ClientDto responseData = clientApplication.createClient(clientDto);
        return CommonVo.create("请求成功", true, responseData);
    }

    //删除客户
    @DeleteMapping("/client")
    public CommonVo<Void> deleteClient(@RequestParam("id") String clientId)
        throws ServerException{
        clientApplication.deleteClient(clientId);
        return CommonVo.create("请求成功",true);
    }

    //更新客户信息
    @PutMapping("/client")
    public CommonVo<ClientDto> updateClient(@RequestBody ClientDto clientDto)
        throws ServerException{
        ClientDto responseData = clientApplication.updateClient(clientDto);
        return CommonVo.create("请求成功", true, responseData);
    }

}

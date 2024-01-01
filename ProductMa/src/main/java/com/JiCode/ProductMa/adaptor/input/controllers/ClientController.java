package com.JiCode.ProductMa.adaptor.input.controllers;

import com.JiCode.ProductMa.adaptor.input.vo.CommonVo;
import com.JiCode.ProductMa.application.ClientApplication;
import com.JiCode.ProductMa.application.dto.AllClientsDto;
import com.JiCode.ProductMa.application.dto.ClientDto;
import com.JiCode.ProductMa.application.dto.SearchClientResponseDto;
import com.JiCode.ProductMa.common.CodeEnum;
import com.JiCode.ProductMa.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class ClientController {
    @Autowired
    ClientApplication clientApplication;

    @GetMapping("hello")
    public String hello() {
        return "hello world";
    }

    // 分页获取客户信息
    @GetMapping("/clients")
    public CommonVo<AllClientsDto> getAllClients(@RequestParam("productId") String productId,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize)
            throws ServerException {
        AllClientsDto allClientsDto = clientApplication.getAllClientsByProductId(productId, pageNo, pageSize);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, allClientsDto);
    }

    // 获取客户详情
    @GetMapping("/client")
    public CommonVo<ClientDto> getClientDetail(@RequestParam("id") String clientId)
            throws ServerException {
        ClientDto responseData = clientApplication.getClientDetail(clientId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }

    // 新建客户
    @PostMapping("/client")
    public CommonVo<ClientDto> createClient(@RequestBody ClientDto clientDto)
            throws ServerException {
        ClientDto responseData = clientApplication.createClient(clientDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }

    // 删除客户
    @DeleteMapping("/client")
    public CommonVo<Void> deleteClient(@RequestParam("id") String clientId)
            throws ServerException {
        clientApplication.deleteClient(clientId);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS);
    }

    // 更新客户信息
    @PutMapping("/client")
    public CommonVo<ClientDto> updateClient(@RequestBody ClientDto clientDto)
            throws ServerException {
        ClientDto responseData = clientApplication.updateClient(clientDto);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }

    // 搜索客户列表
    @GetMapping("/clients/search")
    public CommonVo<SearchClientResponseDto> searchClient(@RequestParam("product_id") String productId,
            @RequestParam("keyword") String keyword)
            throws ServerException {
        SearchClientResponseDto responseData = clientApplication.searchClientByName(productId, keyword);
        return CommonVo.create("请求成功", CodeEnum.SUCCESS, responseData);
    }

}

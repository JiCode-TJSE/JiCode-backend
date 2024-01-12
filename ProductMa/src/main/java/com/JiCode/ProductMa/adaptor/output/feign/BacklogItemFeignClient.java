package com.JiCode.ProductMa.adaptor.output.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesReqDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesResDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.CommonDto;

@FeignClient(name = "ProductDevelopmentService", url = "http://101.37.116.97:8082", path = "/api/productdev/backlogitems")
public interface BacklogItemFeignClient {

    @PostMapping("/names")
    CommonDto<String[]> getMultiNames(@RequestBody BacklogItemNamesReqDto backlogItemNamesReqDto);
}
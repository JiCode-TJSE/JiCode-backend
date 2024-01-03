package com.JiCode.ProductMa.adaptor.output.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesReqDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesResDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.CommonDto;

@FeignClient(name = "ProductDevelopmentService", url = "http://127.0.0.1:4523/m1/3754258-0-default", path = "/api/backlogitem")
public interface BacklogItemFeignClient {

    @GetMapping("/names")
    CommonDto<BacklogItemNamesResDto> getMultiNames(@RequestBody BacklogItemNamesReqDto backlogItemNamesReqDto);
}
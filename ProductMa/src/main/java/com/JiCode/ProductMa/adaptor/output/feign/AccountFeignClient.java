package com.JiCode.ProductMa.adaptor.output.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.JiCode.ProductMa.adaptor.output.feign.dto.CommonDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.UsernamesReqDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.UsernamesResDto;

// TODO feign 客户端的优化

@FeignClient(name = "AccountService", url = "http://127.0.0.1:4523/m1/3754258-0-default", path = "/api/user")
public interface AccountFeignClient {

    @GetMapping("/multiuserInfo")
    CommonDto<UsernamesResDto> getMultiUserInfo(@RequestBody UsernamesReqDto usernamesReqDto);

}

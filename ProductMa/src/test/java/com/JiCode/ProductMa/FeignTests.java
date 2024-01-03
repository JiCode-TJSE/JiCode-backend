package com.JiCode.ProductMa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.JiCode.ProductMa.adaptor.output.feign.AccountFeignClient;
import com.JiCode.ProductMa.adaptor.output.feign.BacklogItemFeignClient;
import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesReqDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.BacklogItemNamesResDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.CommonDto;
import com.JiCode.ProductMa.adaptor.output.feign.dto.UsernamesReqDto;
import com.JiCode.ProductMa.common.CodeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTests {

    @Autowired
    private BacklogItemFeignClient backlogItemFeignClient;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Test
    public void testGetMultiBacklogItemNames() throws Exception {
        BacklogItemNamesReqDto backlogItemNamesReqDto = new BacklogItemNamesReqDto(new String[] { "1", "2", "3" });
        CommonDto<BacklogItemNamesResDto> response = backlogItemFeignClient.getMultiNames(backlogItemNamesReqDto);
        if (response.getCode() == CodeEnum.SUCCESS) {
            System.out.println(response.getData());
        } else {
            System.out.println(response.getMsg());
        }
    }

    @Test
    public void testGetMultiUserInfo() throws Exception {
        UsernamesReqDto usernamesReqDto = new UsernamesReqDto(new String[] { "1", "2", "3" });
        usernamesReqDto.setAccountIdArr(new String[] { "1", "2", "3" });
        var response = accountFeignClient.getMultiUserInfo(usernamesReqDto);
        if (response.getCode() == CodeEnum.SUCCESS) {
            System.out.println(response.getData());
        } else {
            System.out.println(response.getMsg());
        }

    }

}

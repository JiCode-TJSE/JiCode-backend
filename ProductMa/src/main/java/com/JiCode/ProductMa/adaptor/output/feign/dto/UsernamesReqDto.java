package com.JiCode.ProductMa.adaptor.output.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernamesReqDto {
    String[] accountIdArr;

    public UsernamesReqDto(String accountId) {
        this.accountIdArr = new String[] { accountId };
    }

}
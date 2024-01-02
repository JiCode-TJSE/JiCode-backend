package com.JiCode.Account.application.dto;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 账户基本信息
 * @Author fjy
 * @Date 2023-12-27
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String accountId;

    private String organizationId;

    private String email;

    private String phoneNumber;

    private String password;

    private String userName;

    private String name;
}

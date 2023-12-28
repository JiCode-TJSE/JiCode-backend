package com.JiCode.Account.application.dto;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 登录的返回参数
 * @Author fjy
 * @Date 2023-12-28
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {
    List<Account> accountList;
}

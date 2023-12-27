package com.JiCode.Account.application.dto;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description TODO
 * @Author fjy
 * @Date 2023-12-27
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    List<Account> accountList;
}

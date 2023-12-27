package com.JiCode.Account.application;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.service.LoginService;
import com.JiCode.Account.domain.service.SignupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 应用层实现：注册、登录、账号设置、注销账号
 * @Author fjy
 * @Date 2023-12-27
 **/

@Service
public class AccountApplication {
    
    @Autowired
    AccountRepository accountRepository;

    // 注册
    public AccountDto register(AccountAggregation accountAggregation) {
        List<Account> accountList=new ArrayList<>();
        String accountID= accountRepository.insert(accountAggregation);
        accountList.
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountList();
        BeanUtils.copyProperties(accountAggregation, accountDto);

        return accountDto;
    }

    // 登录
    public Boolean login(AccountAggregation accountAggregation) {
        try {
            loginService.loginByEmail(accountAggregation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

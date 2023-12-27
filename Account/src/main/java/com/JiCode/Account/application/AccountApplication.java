package com.JiCode.Account.application;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
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
        try {
            List<Account> accountList = new ArrayList<>();
            // 调用accountRepository的insert方法来插入账号和信息数据
            accountList = accountRepository.insert(accountAggregation);
            if (accountList.isEmpty()) {
                // 注册失败
                return null;
            } else {
                // 注册成功
                AccountDto accountDto = new AccountDto();
                accountDto.setAccountList(accountList);
                return accountDto;
            }
        } catch (Exception e) {
            return null;
        }

    }

    // 登录
    public Boolean login(AccountAggregation accountAggregation) {
        try {
            // 调用accountRepository的checkLogin方法来检查登录
            List<Account> accounts = accountRepository.checkLogin(accountAggregation.getEmail(), accountAggregation.getPassword());
            if (accounts.isEmpty()) {
                // 用户不存在或密码错误
                return false;
            } else {
                // 登录成功
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }



}

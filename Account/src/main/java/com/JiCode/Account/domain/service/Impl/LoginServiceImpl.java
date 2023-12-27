package com.JiCode.Account.domain.service.Impl;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 登录-领域服务实现
 * @Author fjy
 * @Date 2023-12-27
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountRepository accountRepository;
    @Override
    public int loginByEmail(AccountAggregation accountAggregation) {

        // 调用accountRepository的checkLogin方法来检查登录
        List<Account> accounts = accountRepository.checkLogin(accountAggregation.getEmail(), accountAggregation.getPassword());
        if (accounts.isEmpty()) {
            // 用户不存在或密码错误
            return 0;
        } else {
            // 登录成功
            return 1;
        }
    }
}

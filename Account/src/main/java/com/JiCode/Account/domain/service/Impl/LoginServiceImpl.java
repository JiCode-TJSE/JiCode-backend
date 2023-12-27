package com.JiCode.Account.domain.service.Impl;

import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 登录-领域服务实现
 * @Author fjy
 * @Date 2023-12-27
 **/
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountRepository accountRepository;
    @Override
    public int loginByEmail(AccountAggregation accountAggregation) {

        return 1;
    }
}

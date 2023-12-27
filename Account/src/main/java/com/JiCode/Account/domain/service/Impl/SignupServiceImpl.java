package com.JiCode.Account.domain.service.Impl;

import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import com.JiCode.Account.domain.service.LoginService;
import com.JiCode.Account.domain.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 注册-领域服务的实现
 * @Author fjy
 * @Date 2023-12-26
 **/

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public int signUpByEmail(AccountAggregation accountAggregation) {




        return accountRepository.insert(accountAggregation);
    }
}

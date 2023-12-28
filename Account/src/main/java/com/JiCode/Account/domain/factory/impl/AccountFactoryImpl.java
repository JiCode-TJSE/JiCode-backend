package com.JiCode.Account.domain.factory.impl;

import com.JiCode.Account.domain.factory.AccountFactory;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description 工厂模式-创建聚合
 * @Author fjy
 * @Date 2023-12-27
 **/

@Service
public class AccountFactoryImpl implements AccountFactory {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public AccountAggregation createAccount(String accountID, String email, String phoneNumber, String password, String organizationID) {
        AccountAggregation accountAggregation = new AccountAggregation();
        accountAggregation.setAccountID(accountID);
        accountAggregation.setEmail(email);
        accountAggregation.setPhoneNumber(phoneNumber);
        accountAggregation.setPassword(password);
        accountAggregation.setOrganizationID(organizationID);
        accountAggregation.setUserInfoAggregation(userInfoRepository.selectById(accountID));// 调用userinfo的仓储方法
        return accountAggregation;
    }
}

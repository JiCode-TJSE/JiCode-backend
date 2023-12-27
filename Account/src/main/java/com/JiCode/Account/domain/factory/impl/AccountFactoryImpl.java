package com.JiCode.Account.domain.factory.impl;

import com.JiCode.Account.domain.factory.AccountFactory;
import com.JiCode.Account.domain.model.AccountAggregation;
import org.springframework.stereotype.Service;


/**
 * @Description 工厂模式-创建聚合
 * @Author fjy
 * @Date 2023-12-27
 **/

@Service
public class AccountFactoryImpl implements AccountFactory {
    @Override
    public AccountAggregation createAccount(String accountID, String email, String phoneNumber, String password, String organizationID) {
        AccountAggregation accountAggregation = new AccountAggregation();
        accountAggregation.setAccountID(accountID);
        accountAggregation.setEmail(email);
        accountAggregation.setPhoneNumber(phoneNumber);
        accountAggregation.setPassword(password);
        accountAggregation.setOrganizationID(organizationID);
        return accountAggregation;
    }
}

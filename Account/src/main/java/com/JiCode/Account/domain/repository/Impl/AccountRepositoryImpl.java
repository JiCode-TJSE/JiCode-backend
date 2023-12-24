package com.JiCode.Account.domain.repository.Impl;


import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.UserInfo;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.AccountMapper;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public int insert(AccountAggregation accountAggregation) {
        try {
            Account account = new Account();
            BeanUtils.copyProperties(accountAggregation, account);
            String accountID = UUID.randomUUID().toString();
            account.setAccountId(accountID);

            // todo: 调用userinfo的insert（传入参数是accountID）

            return accountMapper.insert(account);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}

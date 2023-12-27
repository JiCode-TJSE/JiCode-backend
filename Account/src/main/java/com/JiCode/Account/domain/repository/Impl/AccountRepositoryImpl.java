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


/**
 * @author Fan Jiayi
 * @date 2023/12/26
 * @description repository的实现类
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public int insert(AccountAggregation accountAggregation) {
        try {
            // 插入一条account表的数据（现在写的默认组织id是2）
            Account account = new Account();
            BeanUtils.copyProperties(accountAggregation, account);
            String accountID = UUID.randomUUID().toString();
            account.setAccountId(accountID);
            account.setEmail((accountAggregation.getEmail()));
            account.setPassword(accountAggregation.getPassword());
            account.setOrganizationId("2");
            account.setPhoneNumber(accountAggregation.getPhoneNumber());
            return accountMapper.insert(account);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    @Override
    public int updateById(AccountAggregation accountAggregation) {
        try{
            Account account = new Account();
            BeanUtils.copyProperties(accountAggregation, account);
            return accountMapper.updateByPrimaryKey(account);
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try{
            return accountMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}

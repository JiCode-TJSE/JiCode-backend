package com.JiCode.Account.domain.repository.Impl;


import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.AccountExample;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.UserInfo;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.AccountMapper;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
//    @Autowired


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

            // todo:插入userinfo表一条数据


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

    // 检查是否可以登录
    @Override
    public int checkLogin(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        AccountExample accountExample=new AccountExample();
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andEmailEqualTo(email).andPasswordEqualTo(password); // 使用邮箱和密码作为查询条件

        // 返回该用户的所有账号列表
        List<Account> accounts = accountMapper.selectByExample(accountExample); // accountMapper是Account表对应的Mapper接口
        return 0;
    }
}

package com.JiCode.Account.domain.repository.Impl;


import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.AccountExample;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.AccountMapper;
import com.JiCode.Account.domain.factory.AccountFactory;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    AccountFactory accountFactory;

    @Override
    public AccountAggregation selectById(String id) {
        try {
            Account account = accountMapper.selectByPrimaryKey(id);
            return accountFactory.createAccount(
                    account.getAccountId(),
                    account.getEmail(),
                    account.getPhoneNumber(),
                    account.getPassword(),
                    account.getOrganizationId()
            );
        } catch (Exception e) {
            System.out.println(e);
            return null;// 失败返回空
        }
    }

    @Override
    public boolean insert(AccountAggregation accountAggregation) {
        try {
            // 插入一条account表的数据
            Account account = new Account();
            account.setEmail(accountAggregation.getEmail());
            account.setPhoneNumber(accountAggregation.getPhoneNumber());
            account.setPassword(accountAggregation.getPassword());
            account.setOrganizationId("1");// （现在写的默认组织id是1）
            String accountID = UUID.randomUUID().toString();
            account.setAccountId(accountID);
            accountMapper.insert(account);// 先插入account，不然后面userinfo没有外键参照插不进去

            // 调用userinfo的仓储插入一条userinfo表的数据
            UserInfoAggregation userInfoReq = accountAggregation.getUserInfoAggregation();
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
            userInfoAggregation.setAccountId(accountID);
            userInfoAggregation.setUserName(userInfoReq.getUserName());
            userInfoRepository.insertUserInfo(userInfoAggregation);
            return true;// 成功返回true
        } catch (Exception e) {
            System.out.println(e);
            return false;// 失败返回false
        }
    }

    @Override
    public boolean updateById(AccountAggregation accountAggregation) {
        try {
            Account account = new Account();
            account.setAccountId(accountAggregation.getAccountID());
            account.setEmail(accountAggregation.getEmail());
            account.setPassword(accountAggregation.getPassword());
            account.setPhoneNumber(accountAggregation.getPhoneNumber());
            account.setOrganizationId(accountAggregation.getOrganizationID());
            accountMapper.updateByPrimaryKey(account);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            accountMapper.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // 检查是否可以登录
    @Override
    public List<Account> checkLogin(String email, String password) {
        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andEmailEqualTo(email).andPasswordEqualTo(password); // 使用邮箱和密码作为查询条件

        // 返回该用户的所有账号列表
        List<Account> accounts = accountMapper.selectByExample(accountExample); // accountMapper是Account表对应的Mapper接口
        if (accounts.isEmpty()) {
            // 用户不存在
            return null;
        } else {
            return accounts;
        }
    }
}

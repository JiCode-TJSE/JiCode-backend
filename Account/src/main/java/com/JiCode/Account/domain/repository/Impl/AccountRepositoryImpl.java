package com.JiCode.Account.domain.repository.Impl;


import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.AccountExample;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.UserInfo;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.AccountMapper;
import com.JiCode.Account.application.UserInfoApplication;
import com.JiCode.Account.application.dto.UserInfoAggregation;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.repository.UserInfoRepository;
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
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public List<Account> insert(AccountAggregation accountAggregation) {
        try {
            // 插入一条account表的数据（现在写的默认组织id是2）
            Account account = new Account();
            BeanUtils.copyProperties(accountAggregation, account);
            String accountID = UUID.randomUUID().toString();
            account.setAccountId(accountID);

            // 调用userinfo的仓储插入一条userinfo表的数据
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation(accountID, null, null, null, null);
            userInfoRepository.insertUserInfo(userInfoAggregation);
            accountMapper.insert(account);
            List<Account> accounts=new ArrayList<>();
            accounts.add(account);
            return accounts; // 成功返回账号id（这里看着是个列表，但其实只会返回一个）
        } catch (Exception e) {
            System.out.println(e);
            return null;// 失败返回空
        }
    }

    @Override
    public int updateById(AccountAggregation accountAggregation) {
        try {
            Account account = new Account();
            BeanUtils.copyProperties(accountAggregation, account);
            return accountMapper.updateByPrimaryKey(account);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return accountMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
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

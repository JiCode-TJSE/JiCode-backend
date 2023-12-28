package com.JiCode.Account.domain.repository;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.domain.model.AccountAggregation;

import java.util.List;


/**
 * 仓储类，用于对数据库进行操作
 * @author Fan Jiayi
 * @date 2023/12/24
 */

public interface AccountRepository {

    public AccountAggregation selectById(String id);
    public boolean insert(AccountAggregation accountAggregation);
    public boolean updateById(AccountAggregation accountAggregation);
    public boolean deleteById(String id);
    // 登录账号查询
    public List<Account> checkLogin(String email, String password);
}

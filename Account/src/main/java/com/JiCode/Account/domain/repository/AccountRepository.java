package com.JiCode.Account.domain.repository;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.domain.model.AccountAggregation;


/**
 * 仓储类，用于对数据库进行操作
 * @author Fan Jiayi
 * @date 2023/12/24
 */

public interface AccountRepository {

    public int insert(AccountAggregation accountAggregation);
}

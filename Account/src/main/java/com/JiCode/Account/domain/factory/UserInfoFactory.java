package com.JiCode.Account.domain.factory;

import com.JiCode.Account.domain.model.UserInfoAggregation;

public interface UserInfoFactory {
    public UserInfoAggregation createUserInfo(
            String id,
            String avatar,
            String gender,
            String name,
            String userName,
            String accountId
    );
}

package com.JiCode.Account.domain.factory;

import com.JiCode.Account.domain.model.UserInfoAggregation;

public interface UserInfoFactory {
    UserInfoAggregation createUserInfo(
            String accountId,
            String avatar,
            String gender,
            String name,
            String userName
    );
}

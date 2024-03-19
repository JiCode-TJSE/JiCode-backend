package com.JiCode.Account.domain.factory.impl;

import com.JiCode.Account.domain.factory.UserInfoFactory;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import org.springframework.stereotype.Service;

@Service
public class UserInfoFactoryImpl implements UserInfoFactory {
    @Override
    public UserInfoAggregation createUserInfo(String accountId, String avatar, String gender, String name, String userName) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        userInfoAggregation.setAccountId(accountId);
        userInfoAggregation.setAvatar(avatar);
        userInfoAggregation.setGender(gender);
        userInfoAggregation.setName(name);
        userInfoAggregation.setUserName(userName);
        return userInfoAggregation;
    }
}
package com.JiCode.Account.domain.repository;

import com.JiCode.Account.domain.model.UserInfoAggregation;

public interface UserInfoRepository {
    public UserInfoAggregation selectById(String id);

    public Boolean insertUserInfo(UserInfoAggregation userInfoAggregation);

    public Boolean updateUserInfo(UserInfoAggregation userInfoAggregation);

    public Boolean deleteUserInfo(String id);
}

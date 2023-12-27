package com.JiCode.Account.domain.repository.Impl;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.UserInfo;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.UserInfoMapper;
import com.JiCode.Account.domain.factory.UserInfoFactory;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserInfoFactory userInfoFactory;
    @Override
    public UserInfoAggregation selectById(String id) {
        try {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
            return userInfoFactory.createUserInfo(
                    userInfo.getId(),
                    userInfo.getAvatar(),
                    userInfo.getGender(),
                    userInfo.getName(),
                    userInfo.getUserName(),
                    userInfo.getAccountId()
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Boolean insertUserInfo(UserInfoAggregation userInfoAggregation) {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userInfoAggregation.getId());
            userInfo.setAvatar(userInfoAggregation.getAvatar());
            userInfo.setGender(userInfoAggregation.getGender());
            userInfo.setName(userInfoAggregation.getName());
            userInfo.setUserName(userInfoAggregation.getUserName());
            userInfo.setAccountId(userInfoAggregation.getAccountId());
            System.out.println(userInfo.getAccountId());
            userInfoMapper.insert(userInfo);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}

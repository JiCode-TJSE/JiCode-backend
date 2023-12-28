package com.JiCode.Account.domain.repository.Impl;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.UserInfo;
import com.JiCode.Account.adaptor.output.dataaccess.mappers.UserInfoMapper;
import com.JiCode.Account.domain.factory.UserInfoFactory;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
                    userInfo.getAccountId(),
                    userInfo.getAvatar(),
                    userInfo.getGender(),
                    userInfo.getName(),
                    userInfo.getUserName()
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
            userInfo.setAccountId(userInfoAggregation.getAccountId());
            userInfo.setAvatar(userInfoAggregation.getAvatar());
            userInfo.setGender(userInfoAggregation.getGender());
            userInfo.setName(userInfoAggregation.getName());
            userInfo.setUserName(userInfoAggregation.getUserName());
            userInfoMapper.insert(userInfo);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateUserInfo(UserInfoAggregation userInfoAggregation) {
        try {
            UserInfo userInfo = new UserInfo();
            UserInfo oldUserInfo = userInfoMapper.selectByPrimaryKey(userInfoAggregation.getAccountId());
            userInfo.setAccountId(oldUserInfo.getAccountId());
            userInfo.setAvatar(userInfoAggregation.getAvatar() == null ? oldUserInfo.getAvatar() : userInfoAggregation.getAvatar());
            userInfo.setGender(userInfoAggregation.getGender() == null ? oldUserInfo.getGender() : userInfoAggregation.getGender());
            userInfo.setName(userInfoAggregation.getName() == null ? oldUserInfo.getName() : userInfoAggregation.getName());
            userInfo.setUserName(userInfoAggregation.getUserName() == null ? oldUserInfo.getUserName() : userInfoAggregation.getUserName());
            userInfoMapper.updateByPrimaryKey(userInfo);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deleteUserInfo(String accountId) {
        try {
            userInfoMapper.deleteByPrimaryKey(accountId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}


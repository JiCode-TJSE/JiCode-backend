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
                    userInfo.getUserName()
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

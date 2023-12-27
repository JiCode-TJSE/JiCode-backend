package com.JiCode.Account.application;

import com.JiCode.Account.application.dto.UserInfoDto;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoApplication {
    @Autowired
    UserInfoRepository userInfoRepository;

    public UserInfoDto selectByUserId(String id) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        userInfoAggregation = userInfoRepository.selectById(id);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfoAggregation, userInfoDto);

        return userInfoDto;
    }

    public Boolean insertUserInfo(UserInfoDto userInfoDto) {
        try {
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
            BeanUtils.copyProperties(userInfoDto, userInfoAggregation);
            userInfoRepository.insertUserInfo(userInfoAggregation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean updateUserInfo(UserInfoDto userInfoDto) {
        try {
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
            BeanUtils.copyProperties(userInfoDto, userInfoAggregation);
            userInfoRepository.updateUserInfo(userInfoAggregation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean deleteUserInfo(String id) {
        try {
            userInfoRepository.deleteUserInfo(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

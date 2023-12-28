package com.JiCode.Account.application;

import com.JiCode.Account.application.dto.UserInfoDto;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserInfoApplication {
    private static final Logger logger = Logger.getLogger(UserInfoApplication.class.getName());
    @Autowired
    UserInfoRepository userInfoRepository;

    // accountId 不存在时，返回 null
    public UserInfoDto selectByUserId(String accountId) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        userInfoAggregation = userInfoRepository.selectById(accountId);
        UserInfoDto userInfoDto = new UserInfoDto();
        if (userInfoAggregation.getAccountId() == null) { // not found
            return null;
        }
        BeanUtils.copyProperties(userInfoAggregation, userInfoDto);
        return userInfoDto;
    }

    public Boolean insertUserInfo(UserInfoDto userInfoDto) {
        try {
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
            if (userInfoRepository.selectById(userInfoDto.getAccountId()) != null) {
                logger.severe("该用户的用户信息已存在，accountId: " + userInfoDto.getAccountId() + "，不做处理");
                return false;
            } else {
                BeanUtils.copyProperties(userInfoDto, userInfoAggregation);
                userInfoRepository.insertUserInfo(userInfoAggregation);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // accountId 不存在时，返回 false
    public Boolean updateUserInfo(UserInfoDto userInfoDto) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        if (userInfoRepository.selectById(userInfoDto.getAccountId()) == null) {
            return false;
        }
        BeanUtils.copyProperties(userInfoDto, userInfoAggregation);
        userInfoRepository.updateUserInfo(userInfoAggregation);
        return true;
    }

    // accountId 不存在时，返回 false
    public Boolean deleteUserInfo(String accountId) {
        try {
            if (userInfoRepository.selectById(accountId) == null) {
                return false;
            }
            userInfoRepository.deleteUserInfo(accountId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

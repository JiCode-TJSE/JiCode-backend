package com.JiCode.Account.application;

import com.JiCode.Account.application.dto.UserInfoDto;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class UserInfoApplication {
    private static final Logger logger = Logger.getLogger(UserInfoApplication.class.getName());
    @Autowired
    UserInfoRepository userInfoRepository;

    // accountId 不存在时，返回 null
    public UserInfoDto selectByUserId(String accountId) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        try {
            userInfoAggregation = userInfoRepository.selectById(accountId);
            UserInfoDto userInfoDto = new UserInfoDto();
            if (userInfoAggregation.getAccountId() == null) { // not found
                return null;
            }
            BeanUtils.copyProperties(userInfoAggregation, userInfoDto);
            return userInfoDto;
        } catch (Exception e) {
            return null;
        }
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

    public List<UserInfoDto> selectMultiUserInfo(List<String> accountIdList) {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();

        for (String accountId : accountIdList) {
            System.out.println(accountId);
        }

        for (String accountId : accountIdList) {
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto = selectByUserId(accountId);
            if (userInfoDto == null) {
                return null;
            }
            userInfoDtoList.add(userInfoDto);
        }

        return userInfoDtoList;
    }
}

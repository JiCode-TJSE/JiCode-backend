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
        System.out.println(userInfoAggregation);
        BeanUtils.copyProperties(userInfoAggregation, userInfoDto);
        return userInfoDto;
    }
}

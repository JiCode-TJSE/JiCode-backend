package com.JiCode.Account.application;

import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.application.dto.UserInfoDto;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import com.JiCode.Account.domain.service.LoginService;
import com.JiCode.Account.domain.service.SignupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 注册、登录、账号设置、注销账号
 * @Author fjy
 * @Date 2023-12-27
 **/

@Service
public class AccountApplication {


    @Autowired
    AccountRepository accountRepository;

    // 注册
    public AccountDto register(String id) {
        UserInfoAggregation userInfoAggregation = new UserInfoAggregation();
        userInfoAggregation = userInfoRepository.selectById(id);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfoAggregation, userInfoDto);

        return accountDto;
    }

    public Boolean login(AccountAggregation accountAggregation) {
        try {
            loginService.loginByEmail(accountAggregation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

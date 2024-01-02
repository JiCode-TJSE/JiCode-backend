package com.JiCode.Account.application;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.application.dto.LoginReqDto;
import com.JiCode.Account.application.dto.LoginResDto;
import com.JiCode.Account.application.dto.RegisterReqDto;
import com.JiCode.Account.domain.factory.AccountFactory;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.model.UserInfoAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description 应用层实现：注册、登录、账号设置、注销账号
 * @Author fjy
 * @Date 2023-12-27
 **/

@Service
public class AccountsApplication {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountFactory accountFactory;

    // 注册
    public boolean register(RegisterReqDto registerReqDto) {
        try {
            AccountAggregation accountAggregation = new AccountAggregation();
            UserInfoAggregation userInfoAggregation = new UserInfoAggregation();

            userInfoAggregation.setUserName(registerReqDto.getUname());

            accountAggregation.setEmail(registerReqDto.getEmail());
            accountAggregation.setPassword(registerReqDto.getPassword());
            accountAggregation.setUserInfoAggregation(userInfoAggregation);

            // 调用accountRepository的insert方法来插入账号和信息数据
            return accountRepository.insert(accountAggregation); // 注册成功
        } catch (Exception e) {
            System.out.println("Register Error: " + e);
            return false;
        }
    }

    // 登录
    public LoginResDto login(LoginReqDto loginReqDto) {
        try {
            // 调用accountRepository的checkLogin方法来检查登录
            List<Account> accounts = accountRepository.checkLogin(loginReqDto.getEmail(), loginReqDto.getPassword());
            if (accounts.isEmpty()) {
                // 用户不存在或密码错误
                return null;
            } else {
                // 登录成功
                LoginResDto loginResDto = new LoginResDto();
                loginResDto.setAccountList(accounts);
                return loginResDto;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // 获取账号信息
    public AccountDto selectById(String id) {
        try {
            AccountAggregation accountAggregation = new AccountAggregation();
            accountAggregation = accountRepository.selectById(id);
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountId(accountAggregation.getAccountID());
            accountDto.setAccountId(accountAggregation.getAccountID());
            accountDto.setEmail(accountAggregation.getEmail());
            accountDto.setOrganizationId(accountAggregation.getOrganizationID());
            accountDto.setPassword(accountAggregation.getPassword());
            accountDto.setUserName(accountAggregation.getUserInfoAggregation().getUserName());
            return accountDto;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // 更新账号信息
    public Boolean updateAccountInfo(AccountDto accountDto) {
        try {
            AccountAggregation accountAggregation = new AccountAggregation();
            accountAggregation.setAccountID(accountDto.getAccountId());
            accountAggregation.setEmail(accountDto.getEmail());
            accountAggregation.setPassword(accountDto.getPassword());
            accountAggregation.setPhoneNumber(accountDto.getPhoneNumber());
            accountAggregation.setOrganizationID(accountDto.getOrganizationId());

            // 调用accountRepository的updateById方法来修改账号信息
            return accountRepository.updateById(accountAggregation); // 修改成功
        } catch (Exception e) {
            System.out.println("Update AccountInfo Error: " + e);
            return false;
        }
    }

    // 注销账号（同时会级联删除对应的个人信息）
    public Boolean deleteAccount(String accountId) {
        try {
            return accountRepository.deleteById(accountId);
        } catch (Exception e) {
            System.out.println("Delete Account Error: " + e);
            return false;
        }
    }

}

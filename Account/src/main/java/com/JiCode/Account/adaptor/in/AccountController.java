package com.JiCode.Account.adaptor.in;

import com.JiCode.Account.application.AccountsApplication;
import com.JiCode.Account.application.dto.*;
import com.JiCode.Account.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 与前端交互的接口
 * @Author fjy
 * @Date 2023-12-27
 **/
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountsApplication accountsApplication;

    @PostMapping("/register")
    public ComResponse<Boolean> register(@RequestBody RegisterReqDto registerReqDto) {
        Boolean registerResult = accountsApplication.register(registerReqDto);
        return ComResponse.success(registerResult);
    }

    @PostMapping("/login")
    public ComResponse<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto=new LoginResDto(accountsApplication.login(loginReqDto).getAccountList());
        if (loginResDto != null) {
            return ComResponse.success(loginResDto);
        } else {
            return ComResponse.error("Account not found");
        }
    }
    @GetMapping("/info")
    public ComResponse<AccountDto> getAccountInfo(@RequestParam("account_id") String account_id) {
        AccountDto accountDto = accountsApplication.selectById(account_id);
        if (accountDto != null) {
            return ComResponse.success(accountDto);
        } else {
            return ComResponse.error("Account not found");
        }
    }

    @PutMapping("/newinfo")
    public ComResponse<AccountDto> updateAccountInfo(@RequestBody AccountDto accountDto) {
        Boolean updateResult = accountsApplication.updateAccountInfo(accountDto);
        return ComResponse.success(updateResult);
    }



    @DeleteMapping("")
    public ComResponse<Boolean> deleteAccount(@RequestParam("account_id") String account_id) {
        Boolean deleteResult = accountsApplication.deleteAccount(account_id);
        if (!deleteResult) {
            return ComResponse.error("Delete failed: the user is not existed");
        }
        return ComResponse.success(deleteResult);
    }
}

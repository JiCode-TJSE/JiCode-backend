package com.JiCode.Account.adaptor.in;

import com.JiCode.Account.application.AccountApplication;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author fjy
 * @Date 2023-12-27
 **/
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountApplication accountApplication;

    @GetMapping("/userinfo")
    public ComResponse<AccountDto> getAccount(@RequestParam("id") String id) {
        AccountDto accountDto = new AccountDto();
        accountDto = accountApplication.selectByUserId(id);
        return ComResponse.success(accountDto);
    }


//    @Autowired
//    AccountApplication accountApplication;

//        @GetMapping("/user-signup")
//        public AccountDto signUp(@RequestParam("id") String id) {
//            AccountDto accountDto = new AccountDto();
//            accountDto = accountApplication.selectByUserId(id);
//            return accountDto;
//        }

}

package com.JiCode.Account.adaptor.in;

import com.JiCode.Account.application.UserInfoApplication;
import com.JiCode.Account.application.dto.UserInfoDto;
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



//    @Autowired
//    UserInfoApplication userInfoApplication;

//        @GetMapping("/user-signup")
//        public UserInfoDto signUp(@RequestParam("id") String id) {
//            UserInfoDto userInfoDto = new UserInfoDto();
//            userInfoDto = userInfoApplication.selectByUserId(id);
//            return userInfoDto;
//        }

}

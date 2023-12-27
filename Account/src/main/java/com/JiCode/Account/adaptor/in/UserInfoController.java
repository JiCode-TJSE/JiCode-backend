package com.JiCode.Account.adaptor.in;

import com.JiCode.Account.application.UserInfoApplication;
import com.JiCode.Account.application.dto.UserInfoDto;
import com.JiCode.Account.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {
    @Autowired
    UserInfoApplication userInfoApplication;

    @GetMapping("/userinfo")
    public ComResponse<UserInfoDto> getUserInfo(@RequestParam("id") String id) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto = userInfoApplication.selectByUserId(id);
        return ComResponse.success(userInfoDto);
    }

    @PutMapping("/userinfo")
    public ComResponse<UserInfoDto> updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return ComResponse.success(userInfoApplication.updateUserInfo(userInfoDto));
    }

    @DeleteMapping("/userinfo")
    public ComResponse<UserInfoDto> deleteUserInfo(@RequestParam("id") String id) {
        return ComResponse.success(userInfoApplication.deleteUserInfo(id));
    }
}

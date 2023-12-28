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
    public ComResponse<UserInfoDto> getUserInfo(@RequestParam("accountId") String accountId) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto = userInfoApplication.selectByUserId(accountId);
        if (userInfoDto == null) {
            return ComResponse.error("user not found");
        }
        return ComResponse.success(userInfoDto);
    }

    @PutMapping("/userinfo")
    public ComResponse<UserInfoDto> updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        Boolean updateResult = userInfoApplication.updateUserInfo(userInfoDto);
        if (!updateResult) {
            return ComResponse.error("update failed(the user is not existed)");
        }
        return ComResponse.success(updateResult);
    }

    // 前端不调用此接口
    @PostMapping("/userinfo")
    public ComResponse<UserInfoDto> insertUserInfo(@RequestBody UserInfoDto userInfoDto) {
        Boolean insertResult = userInfoApplication.insertUserInfo(userInfoDto);
        if (!insertResult) {
            return ComResponse.error("insert failed");
        }
        return ComResponse.success(insertResult);
    }

    // 前端不调用此接口
    @DeleteMapping("/userinfo")
    public ComResponse<UserInfoDto> deleteUserInfo(@RequestParam("accountId") String accountId) {
        Boolean deleteResult = userInfoApplication.deleteUserInfo(accountId);
        if (!deleteResult) {
            return ComResponse.error("delete failed(the user is not existed)");
        }
        return ComResponse.success(deleteResult);
    }
}

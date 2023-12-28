package com.JiCode.Account.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 注册的请求参数
 * @Author fjy
 * @Date 2023-12-28
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {
    String uname;
    String email;
    String password;
}

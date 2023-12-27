package com.JiCode.Account.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String id;
    private String avatar;
    private String gender;
    private String name;
    private String userName;
}

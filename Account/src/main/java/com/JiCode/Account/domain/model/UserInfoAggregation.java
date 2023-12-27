package com.JiCode.Account.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoAggregation {
    private String id;
    private String avatar;
    private String gender;
    private String name;
    private String userName;
}

package com.JiCode.Account.domain.model;

import com.JiCode.Account.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  聚合类 —— Account
 *  @author Fan Jiayi
 *  @date 2023/12/24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAggregation {
    private String accountID;
    private String email;
    private String phoneNumber;
    private String password;
    private String organizationID;
    UserInfoAggregation userInfoAggregation;
}

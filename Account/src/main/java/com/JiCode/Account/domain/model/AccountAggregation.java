package com.JiCode.Account.domain.model;

import com.JiCode.Account.domain.repository.AccountRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  聚合类 —— Account
 *  @author Fan Jiayi
 *  @date 2023/12/24
 */
@Service
@Data
@NoArgsConstructor
public class AccountAggregation {
    @Autowired
    AccountRepository accountRepository;

    private String accountID;
    private String email;
    private String phoneNumber;
    private String password;
    private String organizationID;

    // getters:
    public String getAccountID() {
        return accountID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    /**
     * 工厂模式-创建聚合
     * @param accountID
     * @param email
     * @param phoneNumber
     * @param password
     * @param organizationID
     * @return {@link AccountAggregation}
     */
    static public AccountAggregation createAccount(String accountID, String email, String phoneNumber, String password, String organizationID) {
        AccountAggregation accountAggregation = new AccountAggregation();
        accountAggregation.accountID = accountID;
        accountAggregation.email = email;
        accountAggregation.phoneNumber = phoneNumber;
        accountAggregation.password = password;
        accountAggregation.organizationID = organizationID;
        return accountAggregation;
    }
}

package com.JiCode.Account.domain.factory;

import com.JiCode.Account.domain.model.AccountAggregation;

public interface AccountFactory {
    public AccountAggregation createAccount(
            String accountID,
            String email,
            String phoneNumber,
            String password,
            String organizationID
    );
}

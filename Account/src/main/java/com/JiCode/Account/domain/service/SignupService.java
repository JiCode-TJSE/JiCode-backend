package com.JiCode.Account.domain.service;

import com.JiCode.Account.domain.model.AccountAggregation;


public interface SignupService {
    public int signUpByEmail(AccountAggregation accountAggregation);

}

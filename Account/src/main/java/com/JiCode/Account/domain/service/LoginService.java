package com.JiCode.Account.domain.service;

import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface LoginService {

    public int loginByEmail(AccountAggregation accountAggregation);


}

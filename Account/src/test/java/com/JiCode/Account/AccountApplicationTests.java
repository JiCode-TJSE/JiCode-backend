package com.JiCode.Account;

import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.domain.factory.AccountFactory;
import com.JiCode.Account.domain.model.AccountAggregation;
import com.JiCode.Account.domain.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
@WebAppConfiguration
class AccountApplicationTests {

	@Autowired
	AccountRepository accountRepository;

    @Autowired
    AccountFactory accountFactory;



//	@Test
//    public void testInsert(){
//		AccountAggregation accountAggregation = accountFactory.createAccount(null,"123@qq.com","15022914007","fjy",null);
//		accountRepository.insert(accountAggregation);
////        System.out.println(accountRepository.insert(accountAggregation));
//        // 上面的函数返回的是1
//		System.out.println(accountAggregation);
//	}

//	@Test
//	public void testLogin(){
//		AccountAggregation accountAggregation = accountFactory.createAccount("","123@qq.com","","fjy","");
//		List<Account> list=accountRepository.checkLogin(accountAggregation.getEmail(), accountAggregation.getPassword());
//		System.out.println(list);
//	}
}

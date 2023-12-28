package com.JiCode.Account;

import com.JiCode.Account.adaptor.in.AccountController;
import com.JiCode.Account.adaptor.output.dataaccess.DBModels.Account;
import com.JiCode.Account.application.AccountsApplication;
import com.JiCode.Account.application.dto.AccountDto;
import com.JiCode.Account.application.dto.LoginReqDto;
import com.JiCode.Account.application.dto.RegisterReqDto;
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
@SpringBootTest(classes = com.JiCode.Account.AccountApplication.class)
@WebAppConfiguration
class AccountApplicationTests {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountFactory accountFactory;

    @Autowired
    AccountsApplication accountsApplication;

    @Autowired
    AccountController accountController;

//	@Test
//    public void testInsert(){
//		AccountAggregation accountAggregation = new AccountAggregation();
//        accountAggregation.setEmail("123@qq.com");
//        accountAggregation.setPassword("0110");
//        accountAggregation.setPhoneNumber("00000");
//        System.out.println(accountAggregation);
//
////		accountRepository.insert(accountAggregation);
//        System.out.println(accountRepository.insert(accountAggregation));
//        // 上面的函数返回的是1
//		System.out.println(accountAggregation);
//	}


//    @Test
//    public void testSelectById() {
//        AccountAggregation accountAggregation = new AccountAggregation();
//        accountAggregation = accountRepository.selectById("1");
//        System.out.println(accountAggregation);
//    }

//    @Test
//    public void testRegister() {
//        RegisterReqDto registerReqDto = new RegisterReqDto("樊佳怡", "111@163.com", "111");
//        if (accountsApplication.register(registerReqDto))
//            System.out.println("sucess");
//    }
//}

//        @Test
//    public void testRegisterController() {
//        RegisterReqDto registerReqDto = new RegisterReqDto("开心超人", "000@163.com", "000");
//        System.out.println(accountController.register(registerReqDto));
//    }
//    @Test
//    public void testGetAccountInfoController(){
//        System.out.println(accountController.getAccountInfo("1"));
//    }
//    @Test
//    public void testUpdateAccountInfoController() {
//        AccountDto accountDto = new AccountDto("1", "3", "fjy@tongji.edu.cn", "110", "110");
//        System.out.println(accountController.updateAccountInfo(accountDto));
//    }
//    @Test
//    public void testLoginController() {
//        LoginReqDto loginReqDto = new LoginReqDto("123@qq.com", "0110");
//        System.out.println(accountController.login(loginReqDto));
//    }
}

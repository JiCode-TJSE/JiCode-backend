package com.JiCode.ProductMa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.JiCode.ProductMa.service.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    TestService testService;

    @Test
    public void test() throws Exception {

        // 连续4次读
        log.info("read={}", this.testService.read());
        log.info("read={}", this.testService.read());
        log.info("read={}", this.testService.read());
        log.info("read={}", this.testService.read());

        // 写
        log.info("write={}", this.testService.write());
    }

}
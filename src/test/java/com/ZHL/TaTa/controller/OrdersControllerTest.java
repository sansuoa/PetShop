package com.ZHL.TaTa.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrdersControllerTest {
    private OrderController orderController;

    @Test
    public void test(){
        orderController.payType(32L);
    }
}

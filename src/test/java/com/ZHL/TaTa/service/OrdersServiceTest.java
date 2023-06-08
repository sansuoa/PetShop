package com.ZHL.TaTa.service;

import com.ZHL.TaTa.dto.OrderDto;
import com.ZHL.TaTa.entity.Orders;
import com.ZHL.TaTa.service.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrdersServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        try {
            Long aid = 4L;
            Long[] cids = {3L, 2L};
            Long uid = 1L;
            String username = "订单管理员";
            Orders orders = orderService.create(aid, cids, uid, username);
            System.out.println(orders);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void createall() {
        try {
            Long aid = 4L;
            Long[] cids = {3L, 2L};
            Long uid = 1L;
            String username = "订单管理员";
            Orders orders = orderService.create(aid, cids, uid, username);
            System.out.println(orders);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test2(){
        List<OrderDto> orderDtos = orderService.listByUid(1L);
        System.out.println(orderDtos);
    }
    @Test
    public void create1() {
            orderService.orderOne(1L,51L,3);
    }
}

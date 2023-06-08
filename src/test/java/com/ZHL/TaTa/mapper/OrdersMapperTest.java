package com.ZHL.TaTa.mapper;

import com.ZHL.TaTa.entity.Orders;
import com.ZHL.TaTa.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Orders orders = new Orders();
        orders.setUid(32L);
        orders.setRecvName("小王");
        Integer rows = orderMapper.insertOrder(orders);
        System.out.println("rows=" + rows);
    }


    @Test
    public void insertOrderItem() {
        OrderDetail orderItem = new OrderDetail();
        orderItem.setOid(1L);
        orderItem.setPid(2L);
        orderItem.setName("美丽宠物");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }
}

package com.SHOP.TaTa.mapper;

import com.SHOP.TaTa.entity.Orders;
import com.SHOP.TaTa.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
    Integer insertOrder(Orders orders);
    Integer insertOrderItem(OrderDetail orderItem);
}

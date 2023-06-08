package com.ZHL.TaTa.mapper;

import com.ZHL.TaTa.entity.Orders;
import com.ZHL.TaTa.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
    Integer insertOrder(Orders orders);
    Integer insertOrderItem(OrderDetail orderItem);
}

package com.ZHL.TaTa.service;

import com.ZHL.TaTa.dto.OrderDto;
import com.ZHL.TaTa.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface OrderService extends IService<Orders> {
    Orders create(Long aid, Long[] cids, Long uid, String username);
    List<OrderDto> listByUid(Long uid);
    List<OrderDto> listByUidAndStatus(Long uid, Integer status);
    void orderOne(Long uid,Long pid,Integer amount);
    void orderOneLeave(Long uid,Long pid,Integer amount);
}

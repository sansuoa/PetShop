package com.SHOP.TaTa.service;

import com.SHOP.TaTa.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderDetailService extends IService<OrderDetail>{
    void deletewithOrder(Long oid);
}

package com.ZHL.TaTa.service;

import com.ZHL.TaTa.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderDetailService extends IService<OrderDetail>{
    void deletewithOrder(Long oid);
}

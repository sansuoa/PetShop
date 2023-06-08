package com.ZHL.TaTa.service.impl;

import com.ZHL.TaTa.entity.OrderDetail;
import com.ZHL.TaTa.mapper.OrderDetailMapper;
import com.ZHL.TaTa.service.OrderDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
    @Override
    public void deletewithOrder(Long oid) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,oid);
        this.remove(queryWrapper);
    }
}

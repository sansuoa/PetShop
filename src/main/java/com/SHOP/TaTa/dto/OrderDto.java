package com.SHOP.TaTa.dto;

import com.SHOP.TaTa.entity.OrderDetail;
import com.SHOP.TaTa.entity.Orders;
import lombok.Data;

import java.util.List;


@Data
public class OrderDto extends Orders {
    private List<OrderDetail> Sales;
}

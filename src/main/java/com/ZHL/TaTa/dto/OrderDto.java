package com.ZHL.TaTa.dto;

import com.ZHL.TaTa.entity.OrderDetail;
import com.ZHL.TaTa.entity.Orders;
import lombok.Data;

import java.util.List;


@Data
public class OrderDto extends Orders {
    private List<OrderDetail> Sales;
}

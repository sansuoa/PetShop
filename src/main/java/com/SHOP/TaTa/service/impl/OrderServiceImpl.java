package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.dto.CartDto;
import com.SHOP.TaTa.dto.OrderDto;
import com.SHOP.TaTa.entity.*;
import com.SHOP.TaTa.mapper.OrderMapper;
import com.SHOP.TaTa.service.*;
import com.SHOP.TaTa.service.ex.InsertException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private PetSaleService petSaleService;
    @Autowired
    private PetLeaveService petLeaveService;
    @Override
    public Orders create(Long aid, Long[] cids, Long uid, String username) {
        // 创建当前时间对象
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String s = now.toString();
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartDto> carts = cartService.findByAllId(uid, cids);

        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartDto cart : carts) {
            BigDecimal price = cart.getPrice();
            long l = price.longValue();
            totalPrice += l * cart.getNum();
        }

        // 创建订单数据对象
        Orders orders = new Orders();
        // 补全数据：uid
        orders.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByIdAndCheck(aid, uid);
        // 补全数据：收货地址相关的6项
        orders.setRecvName(address.getName());
        orders.setRecvPhone(address.getPhone());
        orders.setRecvProvince(address.getProvinceCode());
        orders.setRecvCity(address.getCityCode());
        orders.setRecvArea(address.getAreaCode());
        orders.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        orders.setTotalPrice(totalPrice);
        // 补全数据：status
        orders.setStatus(0);
        // 补全数据：下单时间
        orders.setOrderTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(orders);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartDto cart : carts) {
            // 创建订单商品数据
            OrderDetail item = new OrderDetail();
            // 补全数据：setOid(orders.getOid())
            item.setOid(orders.getId());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setName(cart.getName());
            item.setImage(cart.getImage());
            item.setPrice(cart.getPrice().longValue());
            item.setNum(cart.getNum());
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        // 返回
        return orders;
    }

    @Override
    public List<OrderDto> listByUid(Long uid) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUid, uid)
                .orderByDesc(Orders::getCreateTime);

        List<Orders> ordersList = this.list(queryWrapper);

        // 如果使用Java8或以上的版本，可以使用lambda表达式完成对Orders和OrderDetail的整合
        List<OrderDto> orderDtoList = ordersList.stream().map(orders -> {
            OrderDto orderDto = new OrderDto();
            // 将orders复制到orderDto中
            BeanUtils.copyProperties(orders, orderDto);
            // 查询订单的商品信息，将结果存到orderDto的Sales属性中
            LambdaQueryWrapper<OrderDetail> detailWrapper = new LambdaQueryWrapper();
            detailWrapper.eq(OrderDetail::getOid, orders.getId());
            List<OrderDetail> detailList = orderDetailService.list(detailWrapper);
            orderDto.setSales(detailList);
            return orderDto;
        }).collect(Collectors.toList());

        return orderDtoList;
    }

    @Override
    public List<OrderDto> listByUidAndStatus(Long uid, Integer status) {
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Orders::getUid, uid);
            queryWrapper.eq(Orders::getStatus, status); // 添加状态过滤条件
            queryWrapper.orderByDesc(Orders::getCreateTime);
            List<Orders> ordersList = this.list(queryWrapper);

            // 如果使用Java8或以上的版本，可以使用lambda表达式完成对Orders和OrderDetail的整合
            List<OrderDto> orderDtoList = ordersList.stream().map(orders -> {
                OrderDto orderDto = new OrderDto();
                // 将orders复制到orderDto中
                BeanUtils.copyProperties(orders, orderDto);
                // 查询订单的商品信息，将结果存到orderDto的Sales属性中
                LambdaQueryWrapper<OrderDetail> detailWrapper = new LambdaQueryWrapper();
                detailWrapper.eq(OrderDetail::getOid, orders.getId());
                List<OrderDetail> detailList = orderDetailService.list(detailWrapper);
                orderDto.setSales(detailList);
                return orderDto;
            }).collect(Collectors.toList());

            return orderDtoList;
        }

    @Override
    public void orderOne(Long uid,Long pid, Integer amount) {
        //这段很长，但其实就是不停的从其他表里拿东西
        Orders orders = new Orders();
        orders.setUid(uid);
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Address::getUid,uid);
        queryWrapper.eq(Address::getIsDefault,1);
        Address one = addressService.getOne(queryWrapper);
        orders.setRecvName(one.getName());
        orders.setRecvProvince(one.getProvinceCode());
        orders.setRecvPhone(one.getPhone());
        orders.setRecvCity(one.getCityCode());
        orders.setRecvArea(one.getAreaCode());
        orders.setRecvAddress(one.getAddress());
        orders.setOrderTime(LocalDateTime.now());
        PetSale byId = petSaleService.getById(pid);
        BigDecimal price = byId.getPrice();
        long l = price.longValue();
        orders.setTotalPrice(l * amount);
        orders.setStatus(0);
        this.save(orders);
        Long id = orders.getId();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOid(id);
        orderDetail.setPid(pid);
        PetSale byId1 = petSaleService.getById(pid);
        orderDetail.setName(byId1.getName());
        orderDetail.setImage(byId1.getImage());
        orderDetail.setPrice(byId1.getPrice().longValue());
        orderDetail.setNum(amount);
        orderDetailService.save(orderDetail);
    }

    @Override
    public void orderOneLeave(Long uid,Long pid, Integer amount) {
        //这段很长，但其实就是不停的从其他表里拿东西
        Orders orders = new Orders();
        orders.setUid(uid);
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Address::getUid,uid);
        queryWrapper.eq(Address::getIsDefault,1);
        Address one = addressService.getOne(queryWrapper);
        orders.setRecvName(one.getName());
        orders.setRecvProvince(one.getProvinceCode());
        orders.setRecvPhone(one.getPhone());
        orders.setRecvCity(one.getCityCode());
        orders.setRecvArea(one.getAreaCode());
        orders.setRecvAddress(one.getAddress());
        orders.setOrderTime(LocalDateTime.now());
        orders.setIsLeave(1);
        PetLeave byId = petLeaveService.getById(pid);
        BigDecimal price = byId.getPrice();
        long l = price.longValue();
        orders.setTotalPrice(l * amount);
        orders.setStatus(0);
        this.save(orders);
        Long id = orders.getId();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOid(id);
        orderDetail.setPid(pid);
        PetLeave byId1 = petLeaveService.getById(pid);
        orderDetail.setName(byId1.getName());
        orderDetail.setImage(byId1.getImage());
        orderDetail.setPrice(byId1.getPrice().longValue());
        orderDetail.setNum(amount);
        orderDetailService.save(orderDetail);
    }
}

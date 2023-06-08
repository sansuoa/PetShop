package com.ZHL.TaTa.controller;

import com.ZHL.TaTa.common.R;
import com.ZHL.TaTa.controller.ex.AmountException;
import com.ZHL.TaTa.dto.OrderDto;
import com.ZHL.TaTa.dto.PetLeaveDto;
import com.ZHL.TaTa.entity.*;
import com.ZHL.TaTa.service.OrderDetailService;
import com.ZHL.TaTa.service.OrderService;
import com.ZHL.TaTa.service.PetLeaveService;
import com.ZHL.TaTa.service.PetSaleService;
import com.ZHL.TaTa.service.ex.InsertException;
import com.ZHL.TaTa.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private PetSaleService petSaleService;
    @Autowired
    private PetLeaveService petLeaveService;
    @RequestMapping("/create")
    public JsonResult<Orders> create(Long aid, Long[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Orders data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Orders>(OK, data);
    }
    @RequestMapping("/createOne")
    public JsonResult<Void> addToCart(Long pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 返回成功
         PetSale byId = petSaleService.getById(pid);
        Integer amout = byId.getAmout();
        Integer new_amout = amout - amount;
        byId.setAmout(new_amout);
        if (new_amout<0){
            throw new AmountException("商品数量没有那么多");
        }
        orderService.orderOne(uid,pid,amount);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/list")
    public  JsonResult<List<Orders>> list(HttpSession session){
        Long uidFromSession = getUidFromSession(session);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Orders::getUid,uidFromSession);
        List<Orders> list = orderService.list(queryWrapper);
        return new JsonResult<List<Orders>> (OK,list);
    }

    @PostMapping("/pay")
    public  JsonResult<Orders> payType(@RequestParam("orderId") Long id){
        Orders byId = orderService.getById(id);
        byId.setStatus(1);
        byId.setPayTime(LocalDateTime.now());
        orderService.updateById(byId);
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,byId.getId());
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            OrderDetail orderDetail = list.get(i);
            String name = orderDetail.getName();
            Integer num = orderDetail.getNum();
            LambdaQueryWrapper<PetSale> queryWrapper1 = new LambdaQueryWrapper();
            queryWrapper1.eq(PetSale::getName,name);
            PetSale one = petSaleService.getOne(queryWrapper1);
            if (one==null){
                LambdaQueryWrapper<PetLeave> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(PetLeave::getName,name);
                PetLeave one1 = petLeaveService.getOne(queryWrapper2);
                int newLeaveNum = one1.getAmout() - num;
                if (newLeaveNum<0){
                    throw new AmountException("礼包暂时缺少，请减少数量或耐心等待");
                }
                one1.setAmout(newLeaveNum);
                petLeaveService.updateById(one1);
            }
            else {
                int newNum = one.getAmout() - num;
                if (newNum < 0) {
                    throw new AmountException("商品暂时没有这么多，请减少数量或耐心等待");
                }
                one.setAmout(newNum);
                petSaleService.updateById(one);
            }
        }
        return new JsonResult<> (OK,byId);
    }

    @RequestMapping("/listGetOrderDto")
    public JsonResult<List<OrderDto>> getOrderDto(HttpSession session){
        Long uidFromSession = getUidFromSession(session);
        List<OrderDto> orderDtos = orderService.listByUid(uidFromSession);
        return new JsonResult<>(OK,orderDtos);
    }

    @PostMapping("/admitSale")
    public ResponseEntity<String> changeOrderStatus(@RequestBody Map<String, Long> requestBody) {
        Long orderId = requestBody.get("orderId");
        Orders byId = orderService.getById(orderId);
        byId.setStatus(3);
        orderService.updateById(byId);
        return ResponseEntity.ok("Order status updated");
    }
    @PostMapping("/deleteOrder")
    public ResponseEntity<String> deleteOrder(@RequestBody Map<String, Long> requestBody) {
        Long orderId = requestBody.get("orderId");
        Orders byId = orderService.getById(orderId);
        if (byId.getStatus()==1){
            byId.setStatus(4);
        }
        if (byId.getStatus()==0){
            byId.setStatus(5);
        }
        orderService.updateById(byId);
        return ResponseEntity.ok("Order status updated");
    }
    @GetMapping("/ordersByStatus")
    public JsonResult<List<OrderDto>> getOrderDtoByStatus(@RequestParam("status") Integer status, HttpSession session) {
        Long uidFromSession = getUidFromSession(session);
        List<OrderDto> orderDtos = orderService.listByUidAndStatus(uidFromSession, status); // 根据状态过滤订单
        return new JsonResult<>(OK, orderDtos);
    }
    @GetMapping("/page")
    public R<Page<Orders>> page(
            int page,
            int pageSize,
            String number,
            String beginTime,
            String endTime) {
        log.info(
                "订单分页查询：page={}，pageSize={}，number={},beginTimeStr={},endTimeStr={}",
                page,
                pageSize,
                number,
                beginTime,
                endTime);
        // 根据以上信息进行分页查询。
        // 创建分页对象
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        // 创建查询条件对象。
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(number), Orders::getId, number);
        if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate beginTime1 = LocalDate.parse(beginTime, formatter);
            LocalDate endTime1 = LocalDate.parse(endTime, formatter);
            queryWrapper.between(Orders::getOrderTime, beginTime1, endTime1);
        }
        orderService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/goods")
    public R<List<OrderDetail>> getGoodsByOid(@RequestParam("orderId") Long oid, HttpSession session) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(OrderDetail::getOid,oid);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return R.success(list);
    }
    @PutMapping
    public R<String> update(@RequestBody Orders orders){
        boolean b = orderService.updateById(orders);
        if(orders.getStatus()==5){
            LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderDetail::getOid,orders.getId());
            List<OrderDetail> list = orderDetailService.list(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                OrderDetail orderDetail = list.get(i);
                String name = orderDetail.getName();
                Integer num = orderDetail.getNum();
                LambdaQueryWrapper<PetSale> queryWrapper1 = new LambdaQueryWrapper();
                queryWrapper1.eq(PetSale::getName,name);
                PetSale one = petSaleService.getOne(queryWrapper1);
                if (one==null){
                    LambdaQueryWrapper<PetLeave> queryWrapper2 = new LambdaQueryWrapper();
                    queryWrapper2.eq(PetLeave::getName,name);
                    PetLeave one1 = petLeaveService.getOne(queryWrapper2);
                    int newLeaveNum = one1.getAmout() + num;
                    one1.setAmout(newLeaveNum);
                    petLeaveService.updateById(one1);
                }
                else {
                    int newNum = one.getAmout() + num;
                    one.setAmout(newNum);
                    petSaleService.updateById(one);
                }
            }
        }
        if (b!=true){
            return R.error("错误，请联系管理员解决");
        }
        return R.success(orders.getStatus().toString());
    }
    @DeleteMapping("/delete")
    public R<String> deleteSale(Long id){
        orderService.removeById(id);
        orderDetailService.deletewithOrder(id);
        return R.success("删除成功");
    }
    @RequestMapping("/createOneLeave")
    public JsonResult<Void> addToPay(Long pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 返回成功
        PetLeave byId = petLeaveService.getById(pid);
        Integer amout = byId.getAmout();
        Integer newAmount = amout-amount;
        if (newAmount<0){
            throw new AmountException("没有那么多数量的礼包");
        }
        orderService.orderOneLeave(uid,pid,amount);
        return new JsonResult<Void>(OK);
    }
}

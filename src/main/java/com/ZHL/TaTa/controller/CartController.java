package com.ZHL.TaTa.controller;

import com.ZHL.TaTa.dto.CartDto;
import com.ZHL.TaTa.entity.Cart;
import com.ZHL.TaTa.service.CartService;
import com.ZHL.TaTa.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController extends BaseController{
    @Autowired
    private CartService cartService;
    @RequestMapping("addToCart")
    public JsonResult<Void> addToCart(Long pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(OK);
    }
    //这是前端购物车界面
    @GetMapping("/")
    public JsonResult<List<CartDto>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Long uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartDto> data = cartService.getDtoById(uid);
        // 返回成功与数据
        return new JsonResult<List<CartDto>>(OK, data);
    }
    @RequestMapping("/{id}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("id") Long cid, HttpSession session) {
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }
    @RequestMapping("/{id}/num/reduce")
    public JsonResult<Integer> deleteNum(@PathVariable("id") Long cid, HttpSession session) {
        // 从Session中获取uid和username
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer integer = cartService.reduceToCart(cid);
        // 返回成功
        return new JsonResult<Integer>(OK, integer);
    }
    @GetMapping("/list")
    public JsonResult<List<CartDto>> getDtoByCids(Long[] cids, HttpSession session) {
        // 从Session中获取uid
        Long uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartDto> data = cartService.findByAllId(uid, cids);
        // 返回成功与数据
        return new JsonResult<>(OK, data);
    }
    @DeleteMapping("/{cid}")
    public JsonResult deleteById(@PathVariable("cid") Long cid){
        cartService.removeById(cid);
        return new JsonResult(OK,null);
    }
}

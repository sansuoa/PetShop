package com.SHOP.TaTa.service;

import com.SHOP.TaTa.dto.CartDto;
import com.SHOP.TaTa.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface CartService extends IService<Cart> {
    void addToCart(Long uid, Long pid, Integer amount, String username);
    List<CartDto> getDtoById(Long uid);
    Integer addNum(Long cid, Long uid, String username);
    List<CartDto> findByAllId(Long uid,Long[] ids);
    Integer reduceToCart(Long id);
}

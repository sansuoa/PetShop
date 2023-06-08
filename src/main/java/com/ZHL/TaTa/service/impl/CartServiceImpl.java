package com.ZHL.TaTa.service.impl;

import com.ZHL.TaTa.controller.ex.AccessDeniedException;
import com.ZHL.TaTa.dto.CartDto;
import com.ZHL.TaTa.entity.Cart;
import com.ZHL.TaTa.entity.PetSale;
import com.ZHL.TaTa.mapper.CartMapper;
import com.ZHL.TaTa.service.CartService;
import com.ZHL.TaTa.service.PetSaleService;
import com.ZHL.TaTa.service.ex.CarNotFoundException;
import com.ZHL.TaTa.service.ex.CarReduceException;
import com.ZHL.TaTa.service.ex.InsertException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private PetSaleService petSaleService;
    //首先查询购物车表，没有再加，有就number加
    @Override
    public void addToCart(Long uid, Long pid, Integer amount, String username) {
        LambdaQueryWrapper<Cart> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getPid,pid).eq(Cart::getUid,uid);
        Cart one = this.getOne(queryWrapper);
        if (one == null) {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            PetSale byId = petSaleService.getById(pid);
            cart.setPrice(byId.getPrice());
            boolean save = this.save(cart);
            if (save != true) {
                throw new InsertException("出现未知错误，请联系管理员");
            }
        }else {
            Long id = one.getId();
            Integer allnumber = one.getNum() + amount;
            one.setNum(allnumber);
            boolean b = this.updateById(one);
            if (b!=true){
                throw new InsertException("出现未知错误,请联系管理员");
            }
        }
        }

    @Override
    public List<CartDto> getDtoById(Long uid) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUid, uid);
        List<Cart> list = this.list(queryWrapper);

        List<CartDto> list1 = new ArrayList<>();
        for (Cart cart : list) {
            CartDto cartDto = new CartDto();
            // 通过getter方法获取cart的属性值，然后设置到cartDto中对应的属性
            cartDto.setId(cart.getId());
            cartDto.setUid(cart.getUid());
            cartDto.setPid(cart.getPid());
            cartDto.setNum(cart.getNum());
            cartDto.setPrice(cart.getPrice());
            cartDto.setCreateTime(cart.getCreateTime());
            cartDto.setCreateUser(cart.getCreateUser());
            cartDto.setUpdateTime(cart.getUpdateTime());
            cartDto.setUpdateUser(cart.getUpdateUser());
            PetSale byId = petSaleService.getById(cart.getPid());
            String name = byId.getName();
            String image = byId.getImage();
            cartDto.setName(name);
            cartDto.setImage(image);
            list1.add(cartDto);
        }
        return list1;
    }

    @Override
    public Integer addNum(Long cid, Long uid, String username) {
        Cart byId = this.getById(cid);
        if (byId==null){
            throw new CarNotFoundException("购物车未找到数据，请重试");
        }
        if (!byId.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }
        Integer i = byId.getNum() + 1;
        byId.setNum(i);
        byId.setUpdateUser(uid);
        boolean update = this.updateById(byId);
        if (update!=true){
            throw new InsertException("出现未知错误，请联系管理员");
        }
        return i;
    }

    @Override
    public List<CartDto> findByAllId(Long uid,Long[] ids) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Cart::getId,ids);
        List<Cart> carts = this.list(queryWrapper);
        List<CartDto> list1 = new ArrayList<>();
        for (Cart cart : carts) {
            CartDto cartDto = new CartDto();
            // 通过getter方法获取cart的属性值，然后设置到cartDto中对应的属性
            cartDto.setId(cart.getId());
            cartDto.setUid(cart.getUid());
            cartDto.setPid(cart.getPid());
            cartDto.setNum(cart.getNum());
            cartDto.setPrice(cart.getPrice());
            cartDto.setCreateTime(cart.getCreateTime());
            cartDto.setCreateUser(cart.getCreateUser());
            cartDto.setUpdateTime(cart.getUpdateTime());
            cartDto.setUpdateUser(cart.getUpdateUser());
            PetSale byId = petSaleService.getById(cart.getPid());
            String name = byId.getName();
            String image = byId.getImage();
            cartDto.setName(name);
            cartDto.setImage(image);
            list1.add(cartDto);
        }
        Iterator<CartDto> it = list1.iterator();
        //假如uid对不上，就把那个抹了
        while (it.hasNext()) {
            CartDto cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list1;
    }

    @Override
    public Integer reduceToCart(Long id) {
        Cart byId = this.getById(id);
        Integer num = byId.getNum();
        Integer numnext = num-1;
        if (numnext == 0){
            throw new CarReduceException("不能删减至0");
        }
        byId.setNum(numnext);
        this.updateById(byId);
        return numnext;
    }
}

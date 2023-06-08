package com.ZHL.TaTa.service;

import com.ZHL.TaTa.dto.CartDto;
import com.ZHL.TaTa.entity.Cart;
import com.ZHL.TaTa.service.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: 张宏利
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Test
    public void addToCart() {
        try {
            Long uid = 2L;
            Long pid = 38L;
            Integer amount = 1;
            String username = "Tom";
            cartService.addToCart(uid, pid, amount, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void list(){
        List<CartDto> dtoById = cartService.getDtoById(1L);
        System.out.println(dtoById);
    }

    @Test
    public void test2(){
        cartService.addNum(3L,1L,"管理员");
    }

    @Test
    public void test3(){
        Long[] ids = {1L,2L};
        List<CartDto> byAllId = cartService.findByAllId(1L,ids);
        System.out.println(byAllId);
    }

    @Test
    public void test4(){
        cartService.reduceToCart(6L);
    }
}

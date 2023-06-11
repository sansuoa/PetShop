package com.SHOP.TaTa.service;

import com.SHOP.TaTa.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testReg(){
        Address address = new Address();
        address.setUid(1638827459698757634L);
        addressService.insert(address);
    }

    @Test
    public void size(){
        Integer count = addressService.count(1638827459698757634L);
        System.out.println("有"+count+"条数据");

    }

    @Test
    public void testall(){
        Address address = new Address();
        address.setAddress("家里");
        addressService.addNewAddress(1638846052842893313L,"管理员",address);
    }

    @Test
    public void test(){
        List<Address> byUid = addressService.findByUid(1638827459698757634L);
        System.out.println(byUid.size());
    }

    @Test
    public void test2(){
        addressService.updateIsDefault(1638827459698757634L);
    }

    @Test
    public void test3(){
        addressService.updateIsDefault2(1643181176048955394L);
    }

    @Test
    public void testdefault(){
//        addressService.setDefaultAll(1643182648840962049L,1638827459698757634L,"controller");
    }

    @Test
    public void test11(){
        Address lastUpdate = addressService.findLastUpdate(1l);
        System.out.println(lastUpdate);
    }

    @Test
    public void test123(){
        addressService.deleteAndUpdate(2L);
    }
}

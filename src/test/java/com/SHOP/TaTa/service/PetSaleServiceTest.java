package com.SHOP.TaTa.service;

import com.SHOP.TaTa.entity.PetSale;
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
public class PetSaleServiceTest {
    @Autowired
    private PetSaleService petSaleService;

    @Test
    public void login(){
        List<PetSale> petSales = petSaleService.listByCateGoryId(1L);
        System.out.println(petSales);
    }
}

package com.SHOP.TaTa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.SHOP.TaTa.entity.PetSale;

import java.util.List;


public interface PetSaleService extends IService<PetSale> {

    public List<PetSale> getAllPetSales();

    public List<PetSale> listByCateGoryId(Long categoryId);

}

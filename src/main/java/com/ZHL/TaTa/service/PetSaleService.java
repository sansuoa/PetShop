package com.ZHL.TaTa.service;

import com.ZHL.TaTa.dto.PetLeaveDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ZHL.TaTa.entity.PetSale;

import java.util.List;


public interface PetSaleService extends IService<PetSale> {

    public List<PetSale> getAllPetSales();

    public List<PetSale> listByCateGoryId(Long categoryId);

}

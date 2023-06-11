package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.mapper.PetSaleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.SHOP.TaTa.entity.PetSale;
import com.SHOP.TaTa.service.PetSaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class PetSaleServiceImpl extends ServiceImpl<PetSaleMapper, PetSale>implements PetSaleService {

    @Autowired
    private PetSaleMapper petSaleMapper;

    @Override
    public List<PetSale> getAllPetSales() {
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getIsDeleted,0);
        queryWrapper.orderByDesc(PetSale::getUpdateTime);
        return petSaleMapper.selectList(queryWrapper);
    }

    @Override
    public List<PetSale> listByCateGoryId(Long categoryId) {
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getStatus,1);
        queryWrapper.eq(PetSale::getCategoryId,categoryId);
        queryWrapper.orderByDesc(PetSale::getUpdateTime);
        List<PetSale> list = this.list(queryWrapper);
        return list;
    }


}

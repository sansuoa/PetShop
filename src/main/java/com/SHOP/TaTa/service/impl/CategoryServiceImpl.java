package com.SHOP.TaTa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.SHOP.TaTa.common.CustomException;
import com.SHOP.TaTa.entity.Category;
import com.SHOP.TaTa.entity.PetSale;
import com.SHOP.TaTa.mapper.CategoryMapper;
import com.SHOP.TaTa.service.CategoryService;
import com.SHOP.TaTa.service.PetSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements CategoryService {
    @Autowired
    private PetSaleService petSaleService;
    //根据id来删除分类
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<PetSale> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getCategoryId,id);
        int count = petSaleService.count(queryWrapper);
        if(count>0){
            //已经关联了商品
            throw new CustomException("当前分类关联商品，不能删除");
        }
        super.removeById(id);
    }


}

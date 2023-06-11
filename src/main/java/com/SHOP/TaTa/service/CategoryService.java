package com.SHOP.TaTa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.SHOP.TaTa.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}

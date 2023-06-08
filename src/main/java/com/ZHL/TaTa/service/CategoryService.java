package com.ZHL.TaTa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ZHL.TaTa.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}

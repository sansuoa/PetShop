package com.ZHL.TaTa.service;

import com.ZHL.TaTa.common.R;
import com.ZHL.TaTa.dto.PetLeaveDto;
import com.ZHL.TaTa.entity.PetLeave;
import com.ZHL.TaTa.entity.PetSale;
import com.ZHL.TaTa.mapper.PetLeaveMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface PetLeaveService extends IService<PetLeave> {
    //同时保存条件表和礼包表
    public void saveWithPetLeaveCondition(PetLeaveDto petLeaveDto);
    //同时删除条件表和礼包表
    public void removeWithCondition(List<Long> ids);
    //修改时同时返回条件表和礼包表
    public PetLeaveDto selectWithCondition(String id);
    //修改表时先删除才添加
    public void removeAndUpdate(PetLeaveDto petLeaveDto);
    //排出四个，没什么好说的
    public List<PetLeave> getAllPetSales();
}

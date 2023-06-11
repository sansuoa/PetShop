package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.common.CustomException;
import com.SHOP.TaTa.dto.PetLeaveDto;
import com.SHOP.TaTa.entity.Category;
import com.SHOP.TaTa.entity.LeaveCondition;
import com.SHOP.TaTa.entity.PetLeave;
import com.SHOP.TaTa.mapper.PetLeaveMapper;
import com.SHOP.TaTa.service.CategoryService;
import com.SHOP.TaTa.service.LeaveConditionService;
import com.SHOP.TaTa.service.PetLeaveService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PetLeaveServiceImpl extends ServiceImpl<PetLeaveMapper, PetLeave> implements PetLeaveService {
    @Autowired
    private LeaveConditionService leaveConditionService;

    @Autowired
    private CategoryService categoryService;
    @Override
    @Transactional
    public void saveWithPetLeaveCondition(PetLeaveDto petLeaveDto) {
        this.save(petLeaveDto);
        List<LeaveCondition> leaveConditions = petLeaveDto.getLeaveConditions();
        leaveConditions.stream().map((item->{
            item.setLeaveId(petLeaveDto.getId());
            return item;
        })).collect(Collectors.toList());
        leaveConditionService.saveBatch(leaveConditions);
    }

    @Override
    @Transactional
    public void removeWithCondition(List<Long> ids) {
        //这里先试试礼包是不是在售卖
        LambdaQueryWrapper<PetLeave> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.in(ids!=null,PetLeave::getId,ids);
        queryWrapper.eq(PetLeave::getStatus,1);
        int count = this.count(queryWrapper);
        if(count>0){
            throw new CustomException("有礼包正在售卖中，不能删除");
        }
        //先删礼包表
        this.removeByIds(ids);
        //再删条件表
        LambdaQueryWrapper<LeaveCondition> queryWrapper1
                = new LambdaQueryWrapper<>();
                queryWrapper1.in(LeaveCondition::getLeaveId, ids);
        leaveConditionService.remove(queryWrapper1);
    }

    @Override
    public PetLeaveDto selectWithCondition(String id) {
        PetLeave byId = this.getById(id);
        PetLeaveDto petLeaveDto = new PetLeaveDto();
        BeanUtils.copyProperties(byId,petLeaveDto);
        LambdaQueryWrapper<Category> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId,byId.getCategoryId());
        Category one = categoryService.getOne(queryWrapper);
        petLeaveDto.setCategoryName(one.getName());
        LambdaQueryWrapper<LeaveCondition> queryWrapper1
                = new LambdaQueryWrapper<>();
        queryWrapper1.eq(LeaveCondition::getLeaveId,id);
        List<LeaveCondition> list =
                leaveConditionService.list(queryWrapper1);
        petLeaveDto.setLeaveConditions(list);
        return petLeaveDto;
    }

    @Override
    public void removeAndUpdate(PetLeaveDto petLeaveDto) {
        PetLeave petLeave = new PetLeave();
        BeanUtils.copyProperties(petLeaveDto,petLeave);
        LambdaQueryWrapper<PetLeave> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetLeave::getId,petLeave.getId());
        this.update(petLeave,queryWrapper);
        LambdaQueryWrapper<LeaveCondition> queryWrapper1
                = new LambdaQueryWrapper<>();
        queryWrapper1.in(LeaveCondition::getLeaveId, petLeaveDto.getId());
        leaveConditionService.remove(queryWrapper1);
        List<LeaveCondition> leaveConditions = petLeaveDto.getLeaveConditions();
        leaveConditions.stream().map((item->{
            item.setLeaveId(petLeaveDto.getId());
            return item;
        })).collect(Collectors.toList());
        leaveConditionService.saveBatch(leaveConditions);
    }

    @Override
    public List<PetLeave> getAllPetSales() {
        LambdaQueryWrapper<PetLeave> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetLeave::getIsDeleted,0);
        queryWrapper.orderByDesc(PetLeave::getUpdateTime);
        return this.list(queryWrapper);
    }
}

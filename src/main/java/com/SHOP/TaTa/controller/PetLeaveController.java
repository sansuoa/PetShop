package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.common.R;
import com.SHOP.TaTa.dto.PetLeaveDto;
import com.SHOP.TaTa.entity.Category;
import com.SHOP.TaTa.entity.LeaveCondition;
import com.SHOP.TaTa.entity.PetLeave;
import com.SHOP.TaTa.service.CategoryService;
import com.SHOP.TaTa.service.LeaveConditionService;
import com.SHOP.TaTa.service.PetLeaveService;
import com.SHOP.TaTa.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/petLeave")
@Slf4j



public class PetLeaveController extends BaseController{
    @Autowired
    private PetLeaveService petLeaveService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LeaveConditionService leaveConditionService;

    @GetMapping("/newUpdateSale")
    public JsonResult<List<PetLeave>> getPetProducts() {
        List<PetLeave> allPetSales = petLeaveService.getAllPetSales();
        List<PetLeave> petSales = allPetSales.subList(0, 4);
        return new JsonResult<List<PetLeave>>(OK,petSales);
    }
    @PostMapping
    public R<String> save(@RequestBody PetLeaveDto petLeaveDto){
        log.info(petLeaveDto.toString());
        petLeaveService.saveWithPetLeaveCondition(petLeaveDto);
        return R.success("新增礼包成功");
    }

    @GetMapping("/page")
    public R<Page> pageR(int page,int pageSize,String name){
        Page<PetLeave> leavePage = new Page<>(page,pageSize);
        Page<PetLeaveDto> dtoPage = new Page<>();
        LambdaQueryWrapper<PetLeave> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,PetLeave::getName,name);
        queryWrapper.orderByDesc(PetLeave::getUpdateTime);
        petLeaveService.page(leavePage,queryWrapper);
        BeanUtils.copyProperties(leavePage,dtoPage,"records");
        List<PetLeave> records = leavePage.getRecords();
        //这里是把categoryname加上
        List<PetLeaveDto> petLeaveDtos = records.stream().map(item->{
            PetLeaveDto petLeaveDto = new PetLeaveDto();
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if(category!=null){
                String goryname = category.getName();
                //复制record
                BeanUtils.copyProperties(item,petLeaveDto);
                petLeaveDto.setCategoryName(goryname);
            }
            return petLeaveDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(petLeaveDtos);
        return R.success(dtoPage);
    }


        @DeleteMapping
        public R<String> delete(@RequestParam List<Long> ids){
            log.info(ids.toString());
            petLeaveService.removeWithCondition(ids);
            return R.success("删除成功");
        }

    @PostMapping("/status/{status}")
    @Transactional
    public R<String> changeState(@PathVariable Integer status,
                                 @RequestParam String ids){
        log.info(ids.toString());
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            PetLeave petLeave = petLeaveService.getById(id);
            petLeave.setStatus(status);
            petLeaveService.updateById(petLeave);
        }
        return R.success("完成修改");
    }
    @GetMapping("/{id}")
    public R<PetLeaveDto> petLeaveR(@PathVariable String id){
        log.info(id);
        PetLeaveDto petLeaveDto = petLeaveService.selectWithCondition(id);
        return R.success(petLeaveDto);
    }
    @PutMapping
    public R<String> update(@RequestBody PetLeaveDto petLeaveDto){
        log.info(petLeaveDto.toString());
        petLeaveService.removeAndUpdate(petLeaveDto);
        return R.success("修改成功");
    }
    @GetMapping("{id}/details")
    public JsonResult<PetLeaveDto> getById(@PathVariable("id") Long id) {
        // 调用业务对象执行获取数据
        PetLeave data = petLeaveService.getById(id);
        // 返回成功和数据
        LambdaQueryWrapper<LeaveCondition> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LeaveCondition::getLeaveId,id);
        List<LeaveCondition> list = leaveConditionService.list(queryWrapper);
        PetLeaveDto dto = new PetLeaveDto();
        BeanUtils.copyProperties(data, dto);
        dto.setLeaveConditions(list);

        return new JsonResult<>(OK, dto);
    }
    @GetMapping("/listall")
    public JsonResult<List<PetLeave>> getlist(){
        List<PetLeave> list = petLeaveService.list();
        return new JsonResult<>(OK,list);
    }
    @GetMapping("/listByCateGoryId/{categoryId}")
    public JsonResult<List<PetLeave>> listByCateGoryId(@PathVariable("categoryId") Long categoryId){
        LambdaQueryWrapper<PetLeave> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetLeave::getCategoryId,categoryId);
        List<PetLeave> list = petLeaveService.list(queryWrapper);
        return new JsonResult<List<PetLeave>>(OK,list);
    }
}



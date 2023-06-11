package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.common.R;
import com.SHOP.TaTa.dto.PetDto;
import com.SHOP.TaTa.entity.Category;
import com.SHOP.TaTa.entity.PetSale;
import com.SHOP.TaTa.service.CategoryService;
import com.SHOP.TaTa.service.PetSaleService;
import com.SHOP.TaTa.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/petSale")
public class PetSaleController extends BaseController{
    @Autowired
    private PetSaleService petSaleService;
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/newUpdateSale")
    public JsonResult<List<PetSale>> getPetProducts() {
        List<PetSale> allPetSales = petSaleService.getAllPetSales();
        List<PetSale> petSales = allPetSales.subList(0, 4);
        return new JsonResult<List<PetSale>>(OK,petSales);
    }

    @PostMapping
    @Transactional
    public R<String> save (@RequestBody PetSale petSale){
        log.info(petSale.toString());
        petSaleService.save(petSale);
        return R.success("新增商品成功");
    }

    /**
     * 商品分页的查询
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //page1是petsale类，page2是petdto类
        Page<PetSale> page1 = new Page<>(page,pageSize);
        Page<PetDto> page2 = new Page<>();
        LambdaQueryWrapper<PetSale> lambdaQueryWrapper =
                new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name!=null,PetSale::getName,name);
        lambdaQueryWrapper.orderByDesc(PetSale::getUpdateTime);
        petSaleService.page(page1,lambdaQueryWrapper);
        BeanUtils.copyProperties(page1,page2,"records");
        List<PetSale> records = page1.getRecords();
        //通过种类查找找出id对应种类名字
        List<PetDto> list = records.stream().map((item) ->{
            PetDto petDto = new PetDto();
            //别忘了别的属性是空的，也要拷贝进去
            BeanUtils.copyProperties(item,petDto);
            //这里是通过种类id找名称，给他加进dto里
            Long categoryId = item.getCategoryId();
            Category byId = categoryService.getById(categoryId);
            //万一没有种类
            if (byId != null){
                String name1 = byId.getName();
                petDto.setCategoryName(name1);
            }
            return petDto;
        }).collect(Collectors.toList());
        page2.setRecords(list);
        return R.success(page2);
    }

    //这个是修改时返回要修改的数据
    @GetMapping("/{id}")
    public R<PetSale> get(@PathVariable Long id){
        PetSale byId = petSaleService.getById(id);
        return R.success(byId);
    }

    //修改
    @PutMapping
    public R<PetSale> set(@RequestBody PetSale petSale){
        petSaleService.updateById(petSale);
        return R.success(petSale);
    }

    //批量改变状态，单个状态也可以
    @PostMapping("/status/{status}")
    @Transactional
    public R<String> changeState(@PathVariable Integer status,
                                 @RequestParam String ids){
        log.info(ids.toString());
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            PetSale petSale = petSaleService.getById(id);
            petSale.setStatus(status);
            petSaleService.updateById(petSale);
        }
        return R.success("完成修改");
    }

    //批量或者单个删除
    @DeleteMapping
    @Transactional
    public R<String> deleteSale(String ids){
        String[] arr1 = ids.split(",");
        if (arr1 == null){
            return R.error("未知错误！");
        }
        petSaleService.removeByIds(Arrays.asList(arr1));
        return R.success("删除成功");
    }

    /**
     * 根据条件查询商品
     * @param petSale
     * @return
     */
    @GetMapping("/list")
    public R<List<PetSale>> listSale(PetSale petSale){
        LambdaQueryWrapper<PetSale> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getStatus,1);
        queryWrapper.eq(petSale.getCategoryId()!=null,PetSale::getCategoryId,petSale.getCategoryId());
        queryWrapper.orderByAsc(PetSale::getSort).orderByDesc(PetSale::getUpdateTime);
        List<PetSale> petsales = petSaleService.list(queryWrapper);
        return R.success(petsales);
    }
    //这是前端的
    @GetMapping("{id}/details")
    public JsonResult<PetSale> getById(@PathVariable("id") Long id) {
        // 调用业务对象执行获取数据
        PetSale data = petSaleService.getById(id);
        // 返回成功和数据
        return new JsonResult<PetSale>(OK, data);
    }

    @GetMapping("/listall")
    public JsonResult<List<PetSale>> listall() {
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getIsDeleted,0);
        queryWrapper.orderByAsc(PetSale::getSort);
        // 调用业务对象执行获取数据
        List<PetSale> list = petSaleService.list(queryWrapper);
        // 返回成功和数据
        return new JsonResult<List<PetSale>>(OK, list);
    }

    @GetMapping("/listByCateGoryId/{categoryId}")
    public JsonResult<List<PetSale>> listByCateGoryId(@PathVariable("categoryId") Long categoryId){
        List<PetSale> petSales = petSaleService.listByCateGoryId(categoryId);
        return new JsonResult<List<PetSale>>(OK,petSales);
    }

    @GetMapping("/findEightDogFoodSale")
    public JsonResult<List<PetSale>> fingSale(){
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PetSale::getCategoryId,17).orderByDesc(PetSale::getCreateTime).last("limit 8");
        List<PetSale> list = petSaleService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }

    @GetMapping("/search")
    public JsonResult<List<PetSale>> search(@RequestParam("search") String searchQuery) {
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PetSale::getName,searchQuery);
        List<PetSale> list = petSaleService.list(queryWrapper);
        // your code here
        return new JsonResult<>(OK,list);
    }

    @GetMapping("{id}/someWithSale")
    public JsonResult<List<PetSale>> getWithSale(@PathVariable("id") Long id) {
        // 调用业务对象执行获取数据
        PetSale data = petSaleService.getById(id);
        // 返回成功和数据
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetSale::getCategoryId,data.getCategoryId()).last("limit 4");
        List<PetSale> list = petSaleService.list(queryWrapper);
        return new JsonResult(OK, list);
    }
    @GetMapping("/findEightUseSale")
    public JsonResult<List<PetSale>> fingSaleWithUser(){
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PetSale::getCategoryId,19).orderByDesc(PetSale::getCreateTime).last("limit 8");
        List<PetSale> list = petSaleService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }
    @GetMapping("/findEightToySale")
    public JsonResult<List<PetSale>> fingSaleWithToy(){
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PetSale::getCategoryId,18).orderByDesc(PetSale::getCreateTime).last("limit 8");
        List<PetSale> list = petSaleService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }
    @GetMapping("/findEightCatFoodSale")
    public JsonResult<List<PetSale>> fingSaleWithCatFood(){
        LambdaQueryWrapper<PetSale> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PetSale::getCategoryId,16).orderByDesc(PetSale::getCreateTime).last("limit 8");
        List<PetSale> list = petSaleService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }
}

package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.controller.ex.AccessDeniedException;
import com.SHOP.TaTa.controller.ex.AddressCountLimitException;
import com.SHOP.TaTa.controller.ex.AddressNotFoundException;
import com.SHOP.TaTa.controller.ex.DeleteException;
import com.SHOP.TaTa.entity.Address;
import com.SHOP.TaTa.mapper.AddressMapper;
import com.SHOP.TaTa.service.AddressService;
import com.SHOP.TaTa.service.ex.InsertException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Value ("${User.AddressLimit}")
    Integer maxCount;
    //这俩是查找地址的前置方法
    @Override
    public boolean insert(Address address) {
        boolean save = this.save(address);
        return save;
    }

    @Override
    public Integer count(Long uid) {
        LambdaQueryWrapper<Address> queryWrapper =
                new LambdaQueryWrapper();
        queryWrapper.eq(Address::getUid,uid);
        List<Address> list = this.list(queryWrapper);
        int size = list.size();
        System.out.println("里面有"+size+"条数据");
        return size;
    }

    //新增收货地址
    @Override
    public void addNewAddress(Long id, String username, Address address) {
        Integer count = this.count(id);
        if (count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        address.setUid(id);
        address.setName(username);
        //有没有地址？有就不是默认，没有就是默认
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        boolean save = this.save(address);
        if (save!=true){
            throw new InsertException("插入数据时出现未知错误");
        }
    }

    //通过uid查地址
    @Override
    public List<Address> findByUid(Long uid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid,uid);
        queryWrapper.orderByDesc(Address::getIsDefault);
        List<Address> list = this.list(queryWrapper);
        return list;
    }

    //把所有的默认变成0
    @Override
    public void updateIsDefault(Long uid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid,uid);
        queryWrapper.eq(Address::getIsDefault,1);
        Address one = this.getOne(queryWrapper);
        one.setIsDefault(0);
        this.updateById(one);
    }

    //再把现在选的变成1
    @Override
    public void updateIsDefault2(@Param("id") Long id) {
        Address byId = this.getById(id);
        byId.setIsDefault(1);
        this.updateById(byId);
    }



    @Override
    public void setDefaultAll(Long id, Long uid, String username) {
        Address byId = this.getById(id);
        if (byId == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!byId.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        this.updateIsDefault(uid);
        this.updateIsDefault2(id);
    }

    @Override
    public Address findLastUpdate(Long uid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Address::getUpdateTime);
        List<Address> list = this.list(queryWrapper);
        Address address = list.get(0);
        return address;
    }

    //请注意,这是删除地址并且假如删除的是默认，就另外再设一个默认的代码
    @Override
    public void deleteAndUpdate(Long id) {
        Address byId = this.getById(id);
        if (byId == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        Long uid = byId.getUid();
        Integer isDefault = byId.getIsDefault();
        boolean b = this.removeById(id);
        if (b == false){
            throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
        }
        Integer count = this.count(uid);
        if (count == 0){
            return;
        }
        if (isDefault == 1){
            Address lastUpdate = this.findLastUpdate(uid);
            lastUpdate.setIsDefault(1);
            this.updateById(lastUpdate);
        }
    }

    //查地址同时确保uid是一样的
    @Override
    public Address getByIdAndCheck(Long id, Long uid) {
        Address address = this.getById(id);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        return address;
    }


}

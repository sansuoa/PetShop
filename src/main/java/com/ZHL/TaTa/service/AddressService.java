package com.ZHL.TaTa.service;

import com.ZHL.TaTa.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface AddressService extends IService<Address> {
    public boolean insert(Address address);
    public Integer count(Long uid);
    public void addNewAddress(Long id,String username,Address address);
    public List<Address> findByUid(Long uid);
    void updateIsDefault(Long uid);
    void updateIsDefault2(Long id);
    void setDefaultAll(Long id,Long uid,String username);
    Address findLastUpdate(Long id);
    void deleteAndUpdate(Long id);
    Address getByIdAndCheck(Long id,Long uid);
}

package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.entity.Address;
import com.SHOP.TaTa.service.AddressService;
import com.SHOP.TaTa.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    //添加用户的地址
    @RequestMapping("/addNewAddress")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    //通过用户的id来查地址
    @RequestMapping
    public JsonResult<List<Address>> findAddressByUid(HttpSession session){
        Long uidFromSession = getUidFromSession(session);
        List<Address> byUid = addressService.findByUid(uidFromSession);
        return new JsonResult(OK,byUid);
    }

    @RequestMapping("{id}/setDefault")
    public JsonResult<Void> setDefault(@PathVariable("id") Long id,HttpSession session){
        addressService.setDefaultAll(id,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{id}/delete")
    public JsonResult<Void> delete(@PathVariable("id") Long id, HttpSession session) {
        Long uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.deleteAndUpdate(id);
        return new JsonResult<Void>(OK);
    }
    //照例把Petsale的抄过来
    @GetMapping("{id}/details")
    public JsonResult<Address> getById(@PathVariable("id") Long id) {
        // 调用业务对象执行获取数据
        Address data = addressService.getById(id);
        // 返回成功和数据
        return new JsonResult<Address>(OK, data);
    }

    @GetMapping("{aid}")
    public JsonResult<Address> getBy(@PathVariable("aid") Long id) {
        // 调用业务对象执行获取数据
        Address data = addressService.getById(id);
        // 返回成功和数据
        return new JsonResult<Address>(OK, data);
    }

    @PutMapping("/update/{id}")
    public JsonResult<String> updateAddressInfo(@PathVariable("id") Long id, @ModelAttribute Address updatedAddress) {
        // 从 updatedAddress 对象获取更新的地址信息，然后将其保存到数据库中
        addressService.updateById(updatedAddress);
        return new JsonResult<>(OK,"地址更新成功");
    }
}

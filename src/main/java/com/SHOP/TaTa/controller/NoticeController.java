package com.SHOP.TaTa.controller;

import com.SHOP.TaTa.common.R;
import com.SHOP.TaTa.entity.Notice;
import com.SHOP.TaTa.service.NoticeService;
import com.SHOP.TaTa.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;
    @GetMapping("/list")
    public JsonResult<List<Notice>> getNotice() {
        List<Notice> list = noticeService.list();
        return new JsonResult<List<Notice>>(OK,list);
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page objectPage = new Page(page, pageSize);
        LambdaQueryWrapper<Notice> lambdaQueryWrapper = new LambdaQueryWrapper();
        noticeService.page(objectPage,lambdaQueryWrapper);
        return R.success(objectPage);
    }
    @GetMapping("/{id}")
    public R<Notice> get(@PathVariable Long id){
        Notice byId = noticeService.getById(id);
        return R.success(byId);
    }
    @PutMapping("/update")
    public R<String> updateall(@RequestBody Notice notice){
        noticeService.updateById(notice);
        return R.success("用户信息已修改");
    }
}

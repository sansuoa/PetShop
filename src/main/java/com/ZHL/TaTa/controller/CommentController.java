package com.ZHL.TaTa.controller;

import com.ZHL.TaTa.common.R;
import com.ZHL.TaTa.dto.CommentDto;
import com.ZHL.TaTa.entity.Comment;
import com.ZHL.TaTa.entity.Notice;
import com.ZHL.TaTa.entity.PetLeave;
import com.ZHL.TaTa.entity.PetSale;
import com.ZHL.TaTa.service.CommentService;
import com.ZHL.TaTa.service.PetLeaveService;
import com.ZHL.TaTa.service.PetSaleService;
import com.ZHL.TaTa.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController extends BaseController{
    @Autowired
    private CommentService commentService;
    @Autowired
    private PetSaleService petSaleService;
    @Autowired
    private PetLeaveService petLeaveService;
    @GetMapping
    public JsonResult<List<Comment>> listComment(@RequestParam("productId") Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getPid,id);
        queryWrapper.orderByDesc(Comment::getCreatedTime).last("limit 5");
        List<Comment> list = commentService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }
    @PostMapping
    public JsonResult<String> addComment(@RequestParam("productId") Long id , @RequestParam("content")String content, HttpSession session){
        String usernameFromSession = getUsernameFromSession(session);
        Comment comment = new Comment();
        comment.setPid(id);
        comment.setUsername(usernameFromSession);
        comment.setCreatedTime(LocalDateTime.now());
        comment.setContent(content);
        comment.setUpdatedTime(LocalDateTime.now());
        commentService.save(comment);
        return new JsonResult<>(OK,"修改成功");
    }
    @GetMapping("/page")
    public R<Page<CommentDto>> page(int page, int pageSize) {
        Page<Comment> objectPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentService.page(objectPage, lambdaQueryWrapper);

        // 创建一个空的 CommentDto 列表用于记录转换后的 CommentDto 对象
        List<CommentDto> dtos = new ArrayList<>();

        // 遍历查询到的 Comment 列表，将其转换为 CommentDto 对象并添加到 dtos 列表中
        for (Comment comment : objectPage.getRecords()) {
            //这里先把商品名称加进去
            PetSale byId = petSaleService.getById(comment.getPid());
            PetLeave byId1 = petLeaveService.getById(comment.getCid());
            CommentDto dto = new CommentDto();
            if (comment.getPid()!=null) {
                dto.setSalename(byId.getName());
            }if (comment.getCid()!=null){
                dto.setSalename(byId1.getName());
            }
            dto.setId(comment.getId());
            dto.setPid(comment.getPid());
            dto.setUsername(comment.getUsername());
            dto.setContent(comment.getContent());
            dto.setCreatedTime(comment.getCreatedTime());
            dtos.add(dto);
        }

        // 创建 CommentDto 分页对象并设置相应属性
        Page<CommentDto> dtoPage = new Page<>();
        dtoPage.setCurrent(objectPage.getCurrent());
        dtoPage.setPages(objectPage.getPages());
        dtoPage.setSize(objectPage.getSize());
        dtoPage.setTotal(objectPage.getTotal());
        dtoPage.setRecords(dtos);

        return R.success(dtoPage);
    }

    @GetMapping("/{id}")
    public R<Comment> get(@PathVariable Long id){
        Comment comment = commentService.getById(id);
        return R.success(comment);
    }

    @GetMapping("/leave")
    public JsonResult<List<Comment>> listCommentLeave(@RequestParam("productId") Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getCid,id);
        queryWrapper.orderByDesc(Comment::getCreatedTime);
        List<Comment> list = commentService.list(queryWrapper);
        return new JsonResult<>(OK,list);
    }
    @PostMapping("/leaveAdd")
    public JsonResult<String> addCommentLeave(@RequestParam("productId") Long id , @RequestParam("content")String content, HttpSession session){
        String usernameFromSession = getUsernameFromSession(session);
        Comment comment = new Comment();
        comment.setCid(id);
        comment.setUsername(usernameFromSession);
        comment.setCreatedTime(LocalDateTime.now());
        comment.setContent(content);
        comment.setUpdatedTime(LocalDateTime.now());
        commentService.save(comment);
        return new JsonResult<>(OK,"修改成功");
    }
}

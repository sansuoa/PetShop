package com.SHOP.TaTa.service.impl;

import com.SHOP.TaTa.entity.Comment;
import com.SHOP.TaTa.mapper.CommentMapper;
import com.SHOP.TaTa.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}

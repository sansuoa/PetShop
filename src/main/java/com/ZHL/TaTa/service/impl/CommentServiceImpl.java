package com.ZHL.TaTa.service.impl;

import com.ZHL.TaTa.entity.Comment;
import com.ZHL.TaTa.mapper.CommentMapper;
import com.ZHL.TaTa.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}

package com.SHOP.TaTa.dto;

import com.SHOP.TaTa.entity.Comment;
import lombok.Data;


@Data
public class CommentDto extends Comment {
    private String salename;
}

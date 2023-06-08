package com.ZHL.TaTa.dto;

import com.ZHL.TaTa.entity.Comment;
import lombok.Data;


@Data
public class CommentDto extends Comment {
    private String salename;
}

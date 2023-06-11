package com.SHOP.TaTa.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class Comment implements Serializable {
    private Long id;
    private Long pid;
    private Long cid;
    private String username;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}

package com.SHOP.TaTa.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Notice implements Serializable {
    private Long id;
    private String title;
    private String description;

}

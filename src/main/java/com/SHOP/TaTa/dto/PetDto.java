package com.SHOP.TaTa.dto;

import com.SHOP.TaTa.entity.PetSale;
import lombok.Data;

@Data
public class PetDto extends PetSale {

    private String categoryName;

    private Integer copies;

}

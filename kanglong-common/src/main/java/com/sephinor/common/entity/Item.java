package com.sephinor.common.entity;

import lombok.Data;

/**
 * 商品
 */
@Data
public class Item {
    private Integer id;
    private String name;
    //价格(分)
    private Long price;
}

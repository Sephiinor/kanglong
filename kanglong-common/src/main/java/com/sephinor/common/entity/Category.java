package com.sephinor.common.entity;

import lombok.Data;

/**
 *  品牌,类别
 */
@Data
public class Category {
    //id
    private Long id;
    //类名
    private String name;
    //上级品类id
    private Long parentId;
    //是否是最后一别品类
    private boolean leaf;
    //排序值
    private Integer idx;
}

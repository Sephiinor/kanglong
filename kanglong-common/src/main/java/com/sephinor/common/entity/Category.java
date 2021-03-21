package com.sephinor.common.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *  品牌,类别
 */
@Data
@Table(name = "t_category")
public class Category {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //类名
    private String name;
    //上级品类id
    @Column(name = "parent_id")
    private Long parentId;
    //是否是最后一别品类
    private boolean leaf;
    //排序值
    private Integer idx;
}

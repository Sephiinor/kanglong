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
    private Long parentId; //上级品类id
    //修改成Boolean，否则mybatis无法通过get方式提取到。
    private Boolean leaf; //是否是最后一别品类
    private Integer idx; //排序值
}

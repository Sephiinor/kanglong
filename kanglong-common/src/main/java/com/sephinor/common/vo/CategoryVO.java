package com.sephinor.common.vo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  CategoryVO
 */
@Data
public class CategoryVO {

    private Long id;
    //一级类名
    private String c1name;
    //二级类名
    private String c2name;
    //三级类名
    private String c3name;
    //全类名
    private String fullname;
    //是否是最后一别品类
    private boolean leaf;
    //排序值
    private Integer idx;
}

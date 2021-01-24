package com.sephinor.common.entity;

import lombok.Data;

/**
 * 规格参数
 * 就是指在某个特定的组下含有的属性信息，是key-value的形式。
 */
@Data
public class SpecParam {
    private Long id;            //id
    private Long cid;           //所属分类
    private Long groupId;       //规格组id
    private String name;        //名称
    private Boolean numeric;    //是否是数字类型
    private String unit;        //单位
    private Boolean generic;    //是否是常规参数
    private Boolean searching;  //是否可搜索
    private String segments;    //段信息
}

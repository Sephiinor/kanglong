package com.sephinor.common.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 规格参数
 * 就是指在某个特定的组下含有的属性信息，是key-value的形式。
 */
@Data
@Table(name = "t_spec_param")
public class SpecParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //id
    private Long cid;           //所属分类
    @Column(name = "group_id")
    private Long groupId;       //规格组id
    private String name;        //名称
    @Column(name = "`numeric`")
    private Boolean numeric = false;    //是否是数字类型
    private String unit;        //单位
    private Boolean generic = false;    //是否是常规参数
    private Boolean searching = false;  //是否可搜索
    private String segments;    //段信息
}

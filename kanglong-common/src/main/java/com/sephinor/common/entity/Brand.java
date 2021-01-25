package com.sephinor.common.entity;


import lombok.Data;

import javax.persistence.*;

/**
 *  品牌类
 */
@Data
@Table(name="t_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 平台名称
    private String name;
    //图像 , logo
    private String image;
    //品牌首字符
    @Column(name = "firstletter")
    private Character firstLetter;

}

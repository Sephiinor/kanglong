package com.sephinor.common.entity;


import lombok.Data;

/**
 *  品牌类
 */
@Data
public class Brand {

    private Long id;
    // 平台名称
    private String name;
    //图像 , logo
    private String image;
    //品牌首字符
    private char firstLetter;

}

package com.sephinor.common.vo;


import lombok.Data;

import java.util.List;

/**
 *  分页结果
 */
@Data
public class PageResult<T> {

    //--总记录数
    private Long count;
    //--页面数
    private Long pages;

    private List<T> list;
}

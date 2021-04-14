package com.sephinor.common.vo;

import lombok.Data;

/**
 *  CategoryVO
 */
@Data
public class SpecGroupVO {

    private Long id;
    //品类名称
    private String cname;
    //规格组中品类ID
    private Long cid;
    //规格组名称
    private String name;

}

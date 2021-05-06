package com.sephinor.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * standard product unit,标准产品单位
 * 是商品信息聚合的最小单位，是一组可复用、易检索的标准
 * 化信息的集合，该集合描述了一个产品的特性。通俗点讲，属性值、特性相同的商品就可以称为一个SPU。
 */
@Data
@Table(name="t_spu")
public class Spu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //
    private Long brandId ;      //属于的品牌id
    private Long cid1;          //1级类目id
    private Long cid2;          //2级类目id
    private Long cid3;          //3级类目id
    private String title;       //标题
    private String subTitle;    //子标题
    private Boolean salable;    //是否可出售
    private Boolean valid;      //是否有效，逻辑删除用
    private Date createTime;    //创建时间
    private Date lastUpdateTime;//最后修改时间

    private SpuDetail spuDetail; //明细
    private List<Sku> skus ;  // sku列表
}

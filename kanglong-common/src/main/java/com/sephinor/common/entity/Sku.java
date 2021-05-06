package com.sephinor.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Stock keeping unit,库存量单位
 * 即库存进出计量的单位，可以是以件，盒，托盘等为单位。SKU这是
 * 对于大型连锁超市DC（配送中心）物流管理的一个必要的方法。当下已经被我们引申为产品统一编号的简
 * 称，每种产品均对应有唯一的SKU号。
 */
@Data
@Table(name = "t_sku")
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //spuid
    private Long spuId;
    //标题
    private String title;
    //图片
    private String images;
    //价格
    private Long price;
    //自身特殊的规格
    private String ownSpec;
    //商品特殊规格的下标
    private String indexes;
    //是否有效，逻辑删除用
    private Boolean enable;
    //创建时间
    private Date createTime;
    //最后修改时间
    private Date lastUpdateTime;
    //库存量
    private Integer stock;
}

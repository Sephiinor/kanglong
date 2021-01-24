package com.sephinor.common.entity;

import lombok.Data;

/**
 * 库存，含有秒杀信息。
 */
@Data
public class Stock {
    private Long skuId;             //skuId
    private Integer seckillStock;   //可秒杀的库存
    private Integer seckillTotal;   //已秒杀数量
    private Integer stock;          //正常库存
}

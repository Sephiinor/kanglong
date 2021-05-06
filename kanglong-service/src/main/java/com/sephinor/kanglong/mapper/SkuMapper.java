package com.sephinor.kanglong.mapper;


import com.sephinor.common.entity.Sku;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * SkuMapper
 */

public interface SkuMapper extends Mapper<Sku> , IdListMapper<Sku,Long>{



}

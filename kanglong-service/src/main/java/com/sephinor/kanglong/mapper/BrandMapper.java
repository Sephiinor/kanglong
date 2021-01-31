package com.sephinor.kanglong.mapper;


import com.sephinor.common.entity.Brand;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * BrandMapper
 */

public interface BrandMapper extends Mapper<Brand> , IdListMapper<Brand,Long>{



}

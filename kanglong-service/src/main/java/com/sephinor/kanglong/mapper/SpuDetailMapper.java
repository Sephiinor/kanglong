package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpuDetail;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 *  spuDetailMapper
 */
public interface SpuDetailMapper extends Mapper<SpuDetail> , IdListMapper<SpuDetail,Long> {


}

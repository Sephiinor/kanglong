package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpuDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 *  spuDetailMapper
 */
public interface SpuDetailMapper extends Mapper<SpuDetail> , IdListMapper<SpuDetail,Long> {

    @Delete("delete from t_spu_detail where spu_id = #{id}")
    void deleteBySpuId(@Param("id") Long id);
}

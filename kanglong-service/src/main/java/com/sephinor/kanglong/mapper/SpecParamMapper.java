package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpecParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 规格参数
 */

public interface SpecParamMapper extends Mapper<SpecParam> , IdListMapper<SpecParam,Long>{


    @Select("select * from t_spec_param where group_id = #{gid}")
    List<SpecParam> findByGid(@Param("gid") Long gid);
}

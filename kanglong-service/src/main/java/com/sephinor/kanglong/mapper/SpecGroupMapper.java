package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpecGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 规格组
 */

public interface SpecGroupMapper extends Mapper<SpecGroup> , IdListMapper<SpecGroup,Long>{

    /**
     *  根据品类id查找规格参数
     * @param cid
     * @return
     */
    @Select("select * from t_spec_group where cid =#{cid}")
    List<SpecGroup> findByCid(@Param("cid") Long cid);

}

package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.vo.SpecGroupVO;
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
     * @param cid 品类ID
     * @return
     */
    @Select("select * from t_spec_group where cid =#{cid}")
    List<SpecGroup> findByCid(@Param("cid") Long cid);

    /**
     *  查询规格组数据,根据品类排序
     * @return
     */
    @Select("select a.id id , a.name name ,b.name cname  from t_spec_group a INNER JOIN t_category b where a.cid = b.id ORDER BY a.cid,a.id")
    List<SpecGroupVO> findGroups();

    /**
     *  根据id查询规格组
     * @param id
     * @return
     */
    @Select("select * from t_spec_group where id= #{id}")
    SpecGroup findById(@Param("id") Long id);

}

package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.vo.SpecGroupVO;
import org.apache.ibatis.annotations.*;
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
    @Select("select a.id id , a.name name ,b.id cid , b.name cname  from t_spec_group a INNER JOIN t_category b where a.cid = b.id ORDER BY a.cid,a.id")
    List<SpecGroupVO> findGroups();

    /**
     *  根据id查询规格组
     * @param id
     * @return
     */
    @Select("select * from t_spec_group where id= #{id}")
    SpecGroup findById(@Param("id") Long id);



    //按照品类id查询规格组以及参数
    @Select("select * from t_spec_group where cid = #{cid}")
    @Results({
            @Result(property = "id" , column = "id") ,
            @Result(property = "cid" , column = "cid") ,
            @Result(property = "name" , column = "name") ,
            @Result(property = "params" , column = "id" ,
                    many = @Many(select = "com.sephinor.kanglong.mapper.SpecGroupMapper.findParamsByGid"))

    })
    List<SpecGroup> findGroupAndParams(@Param("cid") Long cid) ;

    //按照规格组id查询规格参数
    @Select("select * from t_spec_param where group_id = #{gid}")
    List<SpecParam> findParamsByGid(@Param("gid") Long gid);
}

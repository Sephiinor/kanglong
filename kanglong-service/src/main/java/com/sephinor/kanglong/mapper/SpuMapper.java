package com.sephinor.kanglong.mapper;


import com.sephinor.common.entity.Spu;
import com.sephinor.common.vo.SpuVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * SpuMapper
 */

public interface SpuMapper extends Mapper<Spu> , IdListMapper<Spu,Long>{


    /**
     *  向中间表插入数据
     * @param cid 品类ID
     * @param bid 品牌ID
     * @return
     */
    @Insert("insert into t_category_brand (category_id , brand_id ) values (#(cid) , #(bid))")
    int insertLink(@Param("cid") Long cid , @Param("bid") Long bid);


    /**
     *  查询商品信息
     * @return
     */
    @Select("select a.id id , a.title title , concat(b1.`name` , '/' ,b2.`name` , '/' , b3.name) fullName, c.`name` brandName  from t_spu a , t_category b1 , t_category b2 ,t_category b3 , t_brand c where a.cid1 = b1.id and a.cid2 = b2.id and a.cid3 = b3.id and a.brand_id = c.id")
    List<SpuVO> findSpus();

}

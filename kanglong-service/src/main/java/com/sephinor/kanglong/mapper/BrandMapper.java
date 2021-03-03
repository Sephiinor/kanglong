package com.sephinor.kanglong.mapper;


import com.sephinor.common.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * BrandMapper
 */

public interface BrandMapper extends Mapper<Brand> , IdListMapper<Brand,Long>{

    /**
     *  向中间表插入数据
     */
    @Insert("insert into t_category_brand(category_id ,brand_id) values (#{cid}, #{bid})")
    int insertCategoryBrand(@Param("cid") Long cid,@Param("bid") Long bid);

    /**
     *  根据品类ID插叙品牌
     */
    @Select("select b.* from t_category_brand a  left  outer join t_brand b on a.brand_id = b.id  where a.category_id" +
            " = #{cid}" )
    List<Brand> findByCid(@Param("cid")Long cid);

}

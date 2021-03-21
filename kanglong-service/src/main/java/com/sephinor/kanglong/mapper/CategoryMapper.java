package com.sephinor.kanglong.mapper;

import com.sephinor.common.entity.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 品类
 */

public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category,Long>{

    //查询所有三级品类,名称是全民(name1 / name2 / name3 )
    @Select("select t3.id id , CONCAT(t1.name,' / ', t2.name,' / ' , t3.name) name from t_category t1 LEFT OUTER JOIN t_category t2 on t1.id = t2.parent_id LEFT OUTER JOIN t_category t3 on t2.id = t3.parent_id WHERE t1.parent_id = 0")
    List<Category> findAll();

}

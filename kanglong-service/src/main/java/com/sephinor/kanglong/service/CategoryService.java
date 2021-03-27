package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kanglong.mapper.CategoryMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 品类服务
 */
@Service
public class CategoryService {
	@Resource
	private CategoryMapper categoryMapper ;


	/**
	 * 查询所有品类
	 */
	public List<Category> findAll(){
		return categoryMapper.findAll();
	}


	/**
	 *  查询品牌ID对应品类
	 *
	 */

	public List<Category> findByBrandId(Long bid){
		return categoryMapper.findByBrandId(bid);
	}

	/**
	 *  查询树结构
	 */
	public List<CategoryVO> findTree(){
		return categoryMapper.findTree();
	}

}

package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Category;
import com.sephinor.kanglong.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * @return
	 */
	public List<Category> findAll(){
		return categoryMapper.findAll();
	}




}

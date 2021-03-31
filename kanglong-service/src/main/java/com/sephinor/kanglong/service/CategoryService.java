package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kanglong.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

/**
 * 品类服务
 */
@Service
public class CategoryService {

	Logger logger = LoggerFactory.getLogger(CategoryService.class);

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

	/**
	 *  更新或保存
	 * @param category 品类
	 */
	@Transactional
	public void saveOrUpdate(Category category){
		logger.info("CategoryServicer入参为:"+category.toString());
		 if(null == category.getId()){
		 	//新增
		 	categoryMapper.insert(category);
		 }else{
		 	//更新
		 	categoryMapper.updateByPrimaryKey(category);
		 }
	}

}

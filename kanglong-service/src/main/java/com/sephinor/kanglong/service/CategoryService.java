package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Brand;
import com.sephinor.common.entity.Category;
import com.sephinor.common.exception.ExceptionMessage;
import com.sephinor.common.exception.KangLongException;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kanglong.mapper.BrandMapper;
import com.sephinor.kanglong.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;


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
	@Resource
	private BrandMapper brandMapper ;

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


	/**
	 *  删除品类,不能有子类,不能有关联品牌
	 * @param id
	 */
	@Transactional
	public void deleteById(@RequestParam("id") Long id){
		logger.info("【CategoryService.deleteById】的入参为: id:{} ",id);
		//查找子品牌的数量
		int subCount = categoryMapper.findSubCount(id);
		if(subCount != 0){
			logger.error("品类删除异常,{}存在子类,无法删除",id);
			throw  new KangLongException(ExceptionMessage.CATEGORY_DELETE_ERROR);
		}
		//查看是否有关联品牌
		List<Brand> brandList = brandMapper.findByCid(id);
		if(!CollectionUtils.isEmpty(brandList)){
			logger.error("品类删除异常,{}存在关联品牌,无法删除",id);
			throw  new KangLongException(ExceptionMessage.CATEGORY_DELETE_ERROR);
		}
		logger.info("品类{}无子品类与关联品牌,可执行删除操作",id);
		int result = categoryMapper.deleteByPrimaryKey(id);

		if(result != 0){
			logger.info("品类{}删除成功",id);
		}else{
			logger.error("品类{}删除失败",id);
		}

	}

	/**
	 * 按照id查询品类
	 */
	public Category findById(Long id){

		logger.info("【CategoryService.findById】的入参为: id:{} ",id);

		Category category = categoryMapper.selectByPrimaryKey(id) ;

		logger.info("【CategoryService.findById】根据{}查得品类为{}",id,category);

		if(category == null){
			throw new KangLongException(ExceptionMessage.CATEGORY_NOT_FOUND_ERROR) ;
		}
		return category ;
	}


	/**
	 *  查询子品类
	 * @param pid  父品类id
	 * @return
	 */
	public List<Category> findSubList(Long  pid){
		return categoryMapper.findSubList(pid);
	}

}

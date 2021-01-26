package com.sephinor.kanglong.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kanglong.mapper.BrandMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 品牌服务
 */
@Service
public class BrandService {
	@Autowired
	private BrandMapper brandMapper ;


	/**
	 *  根据id查找品牌
	 * @param id
	 * @return
	 */
	public Brand findById(Long id) {
		Brand brand = new Brand() ;
		brand.setId(id);
		return brandMapper.selectOne(brand);
	}

	/**
	 *  分页查询并排序
	 * @param pno 页号
	 * @param rows 每页记录数
	 * @param sortBy 排序字段
	 * @param desc 是否降序
	 * @param name 品牌名称
	 * @return
	 */
	public PageResult findPagingAndSort(Integer pno,Integer rows ,String sortBy , Boolean desc,String brandName){
		//设置起始页
		PageHelper.startPage( pno , rows ) ;
		//
		Example example = new Example(Brand.class) ;
		//name有效,也可以作为首字母查询
		if(StringUtils.isNotBlank(brandName)){
			example.createCriteria().andLike("name" , "%" + brandName + "%" )
					.orEqualTo("firstLetter" , brandName);
		}
		//处理排序字段
		if(StringUtils.isNotBlank(sortBy)){
			String orderByClause = sortBy + (desc ? " desc" : " asc") ;
			example.setOrderByClause(orderByClause);
		}
		Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(example);
		PageResult result = new PageResult() ;
		result.setCount(page.getTotal());
		result.setList(page);
		return result;
	}
}

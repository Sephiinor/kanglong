package com.sephinor.kanglong.service;


import com.sephinor.common.entity.Brand;
import com.sephinor.kanglong.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌服务
 */
@Service
public class BrandService {
	@Autowired
	private BrandMapper brandMapper ;

	public Brand findById(Long id) {
		Brand brand = new Brand() ;
		brand.setId(id);
		return brandMapper.selectOne(brand);
	}
}

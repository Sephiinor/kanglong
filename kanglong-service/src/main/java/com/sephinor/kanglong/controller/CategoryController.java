package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Brand;
import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kanglong.service.BrandService;
import com.sephinor.kanglong.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * CategoryController
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 *  查询所有品类
	 * @return
	 */
	@GetMapping("/findAll")
	public ResponseEntity<List<Category>> findAll(){
		return  ResponseEntity.ok(categoryService.findAll());
	}

	/**
	 *  根据brandId查询品牌对应的品类
	 * @param bid
	 * @return
	 */
	@GetMapping("/findByBrandId")
	public ResponseEntity<List<Category>> findByBrandId(@RequestParam("bid")Long bid){
		return  ResponseEntity.ok(categoryService.findByBrandId(bid));
	}

}

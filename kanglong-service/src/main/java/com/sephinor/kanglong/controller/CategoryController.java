package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kanglong.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
	 * @param bid 品牌Id
	 * @return
	 */
	@GetMapping("/findByBrandId")
	public ResponseEntity<List<Category>> findByBrandId(@RequestParam("bid")Long bid){
		return  ResponseEntity.ok(categoryService.findByBrandId(bid));
	}

	@GetMapping("/findTree")
	public ResponseEntity<List<CategoryVO>> findTree(){
		return  ResponseEntity.ok(categoryService.findTree());
	}



	//按照品牌id查询品类
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public void saveOrUpdate(@RequestBody  Category category){
		logger.info("已进入"+category.toString());

		categoryService.saveOrUpdate(category) ;
	}

	/**
	 * 按照id删除品类
	 */
	@GetMapping("/deletebyid")
	@ResponseBody
	public void deleteById(@RequestParam("cid") Long cid){
		logger.info("[deleteById]开始删除{}",cid);
		//删除数据库数据
		categoryService.deleteById(cid) ;
	}


	//按照品牌id查询品类
	@GetMapping("/findById")
	public Category findById(@RequestParam("id") Long id){
		return categoryService.findById(id);
	}
}

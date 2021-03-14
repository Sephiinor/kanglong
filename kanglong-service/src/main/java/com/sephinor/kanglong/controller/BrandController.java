package com.sephinor.kanglong.controller;


import com.netflix.discovery.converters.Auto;
import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kanglong.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * BrandController
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

	Logger logger = LoggerFactory.getLogger(BrandController.class);

	@Autowired
	private BrandService brandService ;

	/**
	 *  根据id查询商品信息
	 * @param id 商品id
	 * @return
	 */
	@GetMapping("/findbyid")
	public ResponseEntity<Brand> findById(@RequestParam(value = "id") Long id){
		return ResponseEntity.ok(brandService.findById(id)) ;
	}


	//根据平台Id集合品牌查询
	@GetMapping("findByIds")
	public ResponseEntity<List <Brand>> findByIds(@RequestParam("ids") Long[]ids){
		List<Long> cids = Arrays.asList(ids);
		return ResponseEntity.ok(brandService.findByIds(cids));
	}

	/**
	 *  分页查询
	 * @param pno 页数
	 * @param rows 每页大小
	 * @param sortBy 排序
	 * @param desc 描述
	 * @param name  商品名
	 * @return
	 */



	@GetMapping("/paging")
	public ResponseEntity<PageResult<Brand>> findPagingAndSortByName(@RequestParam(value = "pno", defaultValue = "1") Integer pno,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "desc", defaultValue = "false") Boolean desc, @RequestParam(value = "name", required = false) String name){

		PageResult<Brand> result = brandService.findPagingAndSort(pno, rows, sortBy, desc, name);
		if (result == null || result.getList().size() == 0) {
			return new ResponseEntity<PageResult<Brand>>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/findByCid")
	public ResponseEntity<List<Brand>> findByCid(@RequestParam("cid") Long cid){
		return ResponseEntity.ok(brandService.findByCid(cid));
	}



	@GetMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody Brand brand , @RequestParam("cids") Long[] ids){
		List<Long> cids = Arrays.asList(ids);
		brandService.insertBrand(brand , cids);
		 return ResponseEntity.ok("OK");
	}

}

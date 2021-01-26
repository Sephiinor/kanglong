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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BrandController
 */
@RestController
@RequestMapping("brand")
public class BrandController {

	Logger logger = LoggerFactory.getLogger(BrandController.class);

	@Autowired
	private BrandService brandService ;

	@GetMapping("find")
	public Brand findById(@RequestParam(value = "bid") Long id){
		logger.info("findbyId 入参为"+id);
		Brand  result =brandService.findById(id);
		System.out.println("查询结果为:"+result);
		return  result;
	}

	@GetMapping("paging")
	public ResponseEntity<PageResult<Brand>> findPagingAndSortByName(@RequestParam(value = "pno", defaultValue = "1") Integer pno,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "desc", defaultValue = "false") Boolean desc, @RequestParam(value = "name", required = false) String name){

		PageResult<Brand> result = brandService.findPagingAndSort(pno, rows, sortBy, desc, name);
		if (result == null || result.getList().size() == 0) {
			return new ResponseEntity<PageResult<Brand>>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(result);
	}

}

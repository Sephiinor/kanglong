package com.sephinor.kanglong.controller;


import com.sephinor.common.vo.SpuVO;
import com.sephinor.kanglong.service.SpuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SpuController
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

	Logger logger = LoggerFactory.getLogger(SpuController.class);

	@Autowired
	private SpuService spuService;

	/**
	 *  查询标准商品单位
	 * @return
	 */
	@GetMapping("/findSpus")
	public List<SpuVO> findSpus(){
		return spuService.findSpus();
	}

}

package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Spu;
import com.sephinor.common.vo.SpuVO;
import com.sephinor.kanglong.service.SpuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/saveOrUpdate")
	public void saveOrUpdate(@RequestBody  Spu spu){
		logger.info("【SpuController.saveOrUpdate】的入参为: spu:{} ",spu);
		spuService.saveOrUpdate(spu);
		logger.info("【SpuController.saveOrUpdate】操作成功  spu:{} ",spu);
	}

	/**
	 *  按照id删除spu
	 * @param id
	 */
	@GetMapping("/deleteById")
	public void deleteById(@RequestParam("spuId") Long id){
		logger.info("【SpuController.deleteById】的入参为: id:{} ",id);
		spuService.deleteById(id);
	}

	@GetMapping("/findById")
	public Spu findById(@RequestParam("id") Long id){
		return spuService.findById(id);
	}
}

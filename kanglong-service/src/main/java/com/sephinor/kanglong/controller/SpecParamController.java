package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.vo.SpecParamVO;
import com.sephinor.kanglong.service.SpecGroupService;
import com.sephinor.kanglong.service.SpecParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SpecParamController
 */
@RestController
@RequestMapping("/specParam")
public class SpecParamController {

	Logger logger = LoggerFactory.getLogger(SpecParamController.class);

	@Autowired
	private SpecParamService specParamService;

	@GetMapping("/findByCid")
	public List<SpecParamVO> findByCid(@RequestParam("cid") Long cid){
		logger.info("【SpecParamController.findByCid】的入参为: cid:{} ",cid);
		return specParamService.findByCid(cid) ;
	}

}

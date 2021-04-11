package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Category;
import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.SpecGroupVO;
import com.sephinor.kanglong.service.CategoryService;
import com.sephinor.kanglong.service.SpecGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SpecGroupController
 */
@RestController
@RequestMapping("/specGroup")
public class SpecGroupController {

	Logger logger = LoggerFactory.getLogger(SpecGroupController.class);

	@Autowired
	private SpecGroupService specGroupService;

	/**
	 *  按照品类id查询规格参数
	 * @return
	 */
	@GetMapping("/findByCid")
	public List<SpecGroup> findByCid(@RequestParam("cid") Long  cid){
		logger.info("【SpecGroupController.findByCid】的入参为: cid:{} ",cid);
		return specGroupService.findByCid(cid);

	}

	/**
	 *  查询所有规格组,按照品类排序
	 * @return
	 */
	@GetMapping("/findGroups")
	public  List<SpecGroupVO> findGroup(){
		return specGroupService.findGroup();
	}

}

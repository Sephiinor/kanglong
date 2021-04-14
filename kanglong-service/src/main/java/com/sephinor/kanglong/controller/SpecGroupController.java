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
	 *  按照规格组id查询规格参数
	 * @return
	 */
	@GetMapping("/findById")
	public SpecGroup findById(@RequestParam("id") Long  id){
		logger.info("【SpecGroupController.findById】的入参为: id:{} ",id);
		return specGroupService.findById(id);

	}

	/**
	 *  查询所有规格组,按照品类排序
	 * @return
	 */
	@GetMapping("/findGroups")
	public  List<SpecGroupVO> findGroup(){
		return specGroupService.findGroup();
	}


	/**
	 *  查询所有规格组,按照品类排序
	 * @return
	 */
	@PostMapping("/saveOrUpdate")
	public  void  saveOrUpdateGroup(@RequestBody SpecGroup group ){
		specGroupService.saveOrUpdateGroup(group);
	}


	/**
	 * 按照id删除品类
	 */
	@GetMapping("/deletebyId")
	@ResponseBody
	public void deleteById(@RequestParam("id") Long id){
		logger.info("【SpecGroupController.deleteById】的入参为: id:{} ",id);
		//按照id删除规格组
		specGroupService.deleteById(id) ;
	}

}

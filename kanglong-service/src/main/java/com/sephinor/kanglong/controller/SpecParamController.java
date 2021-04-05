package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.SpecParam;
import com.sephinor.kanglong.service.SpecGroupService;
import com.sephinor.kanglong.service.SpecParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpecParamController
 */
@RestController
@RequestMapping("/specParam")
public class SpecParamController {

	Logger logger = LoggerFactory.getLogger(SpecParamController.class);

	@Autowired
	private SpecParamService specParamService;

}

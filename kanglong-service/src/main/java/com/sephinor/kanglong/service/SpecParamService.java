package com.sephinor.kanglong.service;

import com.sephinor.common.vo.SpecParamVO;
import com.sephinor.kanglong.mapper.SpecParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格参数服务
 */
@Service
public class SpecParamService {

	Logger logger = LoggerFactory.getLogger(SpecParamService.class);

	@Resource
	private SpecParamMapper specParamMapper;

	public List<SpecParamVO> findByCid(Long cid){
		return specParamMapper.findByCid(cid) ;
	}
}

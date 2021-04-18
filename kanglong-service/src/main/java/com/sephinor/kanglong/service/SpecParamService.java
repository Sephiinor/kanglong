package com.sephinor.kanglong.service;

import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.vo.SpecParamVO;
import com.sephinor.kanglong.mapper.SpecParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	/**
	 *  保存或更新规格参数
	 * @param specParam
	 */
	@Transactional
	public void saveOrUpdateParam(SpecParam specParam){

		if(specParam.getId() == null){
			logger.info("【SpecParamService.saveOrUpdateParam】执行新增操作,参数为{}",specParam);
			specParamMapper.insert(specParam);
		}else{
			logger.info("【SpecParamService.saveOrUpdateParam】执行更新操作,参数为{}",specParam);
			specParamMapper.updateByPrimaryKey(specParam);
		}
	}

	/**
	 *  根据主键ID查询规格参数
	 * @param id
	 * @return
	 */
	public  SpecParam findById(Long id){
		return specParamMapper.selectByPrimaryKey(id);
	}
}

package com.sephinor.kanglong.service;

import com.sephinor.common.vo.SpuVO;
import com.sephinor.kanglong.mapper.SpuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标准商品单位
 */
@Service
public class SpuService {

	Logger logger = LoggerFactory.getLogger(SpuService.class);

	@Resource
	private SpuMapper spuMapper;


	/**
	 *  查询商品类列表
	 * @return
	 */
	public List<SpuVO> findSpus(){

		return spuMapper.findSpus();
	}

}

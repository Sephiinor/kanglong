package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Brand;
import com.sephinor.common.entity.Category;
import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.exception.ExceptionMessage;
import com.sephinor.common.exception.KangLongException;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kanglong.mapper.BrandMapper;
import com.sephinor.kanglong.mapper.CategoryMapper;
import com.sephinor.kanglong.mapper.SpecGroupMapper;
import com.sephinor.kanglong.mapper.SpecParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格组服务
 */
@Service
public class SpecGroupService {

	Logger logger = LoggerFactory.getLogger(SpecGroupService.class);

	@Resource
	private SpecGroupMapper specGroupMapper;

	@Resource
	private SpecParamMapper specParamMapper;

	/**
	 *  根据品类id查找规格参数
	 * @param cid
	 * @return
	 */
	public  List<SpecGroup> findByCid(Long cid){
		logger.info("【SpecGroupService.findByCid】的入参为: cid:{} ",cid);
		List<SpecGroup>  list=specGroupMapper.findByCid(cid);
		if(!CollectionUtils.isEmpty(list)){
			logger.info("【SpecGroupService.findByCid】 list不为空,开始遍历");
			for (SpecGroup group : list ) {
				List<SpecParam> params = specParamMapper.findByCid(group.getId());
				group.setParams(params);
			}
		}
		return list;
	}

}

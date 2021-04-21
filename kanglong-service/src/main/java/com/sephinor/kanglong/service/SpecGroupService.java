package com.sephinor.kanglong.service;

import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.exception.KangLongException;
import com.sephinor.common.vo.SpecGroupVO;
import com.sephinor.kanglong.mapper.SpecGroupMapper;
import com.sephinor.kanglong.mapper.SpecParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		logger.info("【SpecGroupService.findByCid】 list{}",list.size()==0?"为空,不遍历":"不为空,开始遍历");
		if(!CollectionUtils.isEmpty(list)){
			for (SpecGroup group : list ) {
				List<SpecParam> params = specParamMapper.findByGid(group.getId());
				group.setParams(params);
			}
		}
		return list;
	}

	/**
	 *  查询规格组,按照品类排序
	 * @return
	 */
	public  List<SpecGroupVO> findGroup(){
		return specGroupMapper.findGroups();
	}


	/**
	 *  插入规格组
	 * @param group
	 */
	public void saveOrUpdateGroup(SpecGroup group){
		logger.info("【SpecGroupService.saveOrUpdateGroup】的入参为: group:{} ",group);
		if("".equals(group.getId()) || null == group.getId()) {
			logger.info("【SpecGroupService.saveOrUpdateGroup】新增规格组,group={} ",group);
			specGroupMapper.insert(group);
		}else {
			logger.info("【SpecGroupService.saveOrUpdateGroup】修改规格组,group={} ",group);
			specGroupMapper.updateByPrimaryKey(group);
		}
	}


	public SpecGroup findById(Long  id){
		return  specGroupMapper.findById(id);
	}


	/**
	 *  删除规格组,不能有子类,不能有关联品牌
	 * @param id
	 */
	@Transactional
	public void deleteById(@RequestParam("id") Long id){
		logger.info("【SpecGroupService.deleteById】的入参为: id:{} ",id);
		List<SpecParam> params = specParamMapper.findByGid(id);
		if(CollectionUtils.isEmpty(params)){
			logger.info("【SpecGroupService.deleteById】开始执行删除{}",id);
			specGroupMapper.deleteByPrimaryKey(id);
		}else{
			logger.error("[{}]该组还有子类,不允许删除",id);
			throw new KangLongException("该组含子类,不允许删除");
		}

	}

	/**
	 * 按照品类id查询规格组和参数
	 */
	public List<SpecGroup> findGroupAndParamsByCid(Long cid){
		logger.info("【SpecGroupService.findGroupAndParamsByCid】的入参为: cid:{} ",cid);
		List<SpecGroup> list = specGroupMapper.findGroupAndParams(cid);
		logger.info("【SpecGroupService.findGroupAndParamsByCid】的入参为: cid:{},出参为{} ",cid,list);
		return list;
	}


	/**
	 *  按照规格组id查询规格参数
	 * @param gid
	 * @return
	 */
	public  List<SpecParam> findParamByGid(Long gid){
		logger.info("【SpecGroupService.findParamByGid】的入参为: gid:{} ",gid);
		List<SpecParam>  list=specGroupMapper.findParamsByGid(gid);
		logger.info("【SpecGroupService.findParamByGid】的入参为: gid:{},出参为{} ",gid,list);

		return list;
	}

}

package com.sephinor.kanglong.service;

import com.sephinor.common.entity.Sku;
import com.sephinor.common.entity.Spu;
import com.sephinor.common.entity.SpuDetail;
import com.sephinor.common.vo.SpuVO;
import com.sephinor.kanglong.mapper.SkuMapper;
import com.sephinor.kanglong.mapper.SpuDetailMapper;
import com.sephinor.kanglong.mapper.SpuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Resource
	private SpuDetailMapper spuDetailMapper;

	@Resource
	private SkuMapper skuMapper;

	@Resource
	private CategoryService categoryService;

	/**
	 *  查询商品类列表
	 * @return
	 */
	public List<SpuVO> findSpus(){

		return spuMapper.findSpus();
	}

	/**
	 *  商品保存或更新
	 * @param spu
	 */
	@Transactional
	public void saveOrUpdate(Spu spu){

		Long cid2 =categoryService.findById(spu.getCid3()).getParentId();
		Long cid1 =categoryService.findById(cid2).getParentId();
		spu.setCid1(cid1);
		spu.setCid2(cid2);

		if(null == spu.getId()){

			logger.info("【SpuService.saveOrUpdate】 spuMapper  开始执行插入操作的入参为: spu:{} ",spu);
 			spuMapper.insert(spu);

			//detail
			SpuDetail detail = spu.getSpuDetail();
			detail.setSpuId(spu.getId());
			logger.info("【SpuService.saveOrUpdate】 spuDetailMapper 开始执行插入操作的入参为: spuDetail:{} ",detail);
			spuDetailMapper.insert(detail);
			//保存sku集合

			for (Sku sku : spu.getSkus()){
				sku.setSpuId(spu.getId());
				skuMapper.insert(sku);
			}
		}else{
			logger.info("【SpuService.saveOrUpdate】 spuMapper开始执行更新操作的入参为: spu:{} ",spu);
			spuMapper.updateByPrimaryKey(spu);
			//detail
			SpuDetail detail = spu.getSpuDetail();
			detail.setSpuId(spu.getId());
			logger.info("【SpuService.saveOrUpdate】 spuDetailMapper 开始执行更新操作的入参为: spuDetail:{} ",detail);
			spuDetailMapper.updateByPrimaryKey(detail);
			//skus
			for (Sku sku: spu.getSkus() ) {
				sku.setSpuId(spu.getId());
				if( null ==sku.getId()){
					logger.info("【SpuService.saveOrUpdate】  skuMapper开始执行更新操作的入参为: sku:{} ",sku);
					skuMapper.insert(sku);
				}else{
					logger.info("【SpuService.saveOrUpdate】  skuMapper开始更新更新操作的入参为: sku:{} ",sku);
					skuMapper.updateByPrimaryKey(sku);
				}
			}
		}

	}

}

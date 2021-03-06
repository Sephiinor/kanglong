package com.sephinor.kanglong.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sephinor.common.entity.Brand;
import com.sephinor.common.exception.ExceptionType;
import com.sephinor.common.exception.KangLongException;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kanglong.controller.BrandController;
import com.sephinor.kanglong.mapper.BrandMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 品牌服务
 */
@Service
public class BrandService {

	Logger logger = LoggerFactory.getLogger(BrandService.class);

	@Resource
	private BrandMapper brandMapper ;

	@Value("${com.sephinor.image.addr9000}")
	private String imageAddr9000;
	@Value("${com.sephinor.image.addr9001}")
	private String imageAddr9001;

	/**
	 *  根据id查找品牌
	 * @param id
	 * @return
	 */
	public Brand findById(Long id) {
		Brand brand = brandMapper.selectByPrimaryKey(id);
		if(brand == null){
			new Exception("商品信息无");
		}
		return brand;

	}


	// 按照Ids集合查询品牌
	public List<Brand> findByIds(List<Long> ids){
		List<Brand> list = brandMapper.selectByIdList(ids) ;
		if(CollectionUtils.isEmpty(list)){
			throw  new KangLongException(ExceptionType.CATEGORY_NOT_FOUND);
		}
		return  list;
	}



	/**
	 *  分页查询并排序
	 * @param pno 页号
	 * @param rows 每页记录数
	 * @param sortBy 排序字段
	 * @param desc 是否降序
	 * @param brandName 品牌名称
	 * @return
	 */
	public PageResult findPagingAndSort(Integer pno,Integer rows ,String sortBy , Boolean desc,String brandName){
		//设置起始页
		PageHelper.startPage( pno , rows ) ;
		//
		Example example = new Example(Brand.class) ;
		//name有效,也可以作为首字母查询
		if(StringUtils.isNotBlank(brandName)){
			example.createCriteria().andLike("name" , "%" + brandName + "%" )
					.orEqualTo("firstLetter" , brandName);
		}
		//处理排序字段
		if(StringUtils.isNotBlank(sortBy)){
			String orderByClause = sortBy + (desc ? " desc" : " asc") ;
			example.setOrderByClause(orderByClause);
		}
		Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(example);
		PageResult result = new PageResult() ;
		result.setCount(page.getTotal());
		result.setList(page);
		return result;
	}

	/**
	 *  保存品牌,同时指定所属的品类集合
	 * @param brand
	 * @param cids
	 */
	@Transactional
	public  void insertBrand(Brand brand, List<Long> cids){

		brand.setId(null);
		// 执行插入 , id由数据库自动生成, 自动回值
		int count = brandMapper.insert(brand);
		if(count != 1){
			throw new KangLongException(ExceptionType.BRAND_SAVE_ERROR);
		}

		//向中间表插入记录
		for (Long cid : cids) {
			count = brandMapper.insertCategoryBrand(cid,brand.getId());
			if(count != 1){
				throw new KangLongException(ExceptionType.CATEGORY_NOT_FOUND);
			}
		}
	}

	/**
	 *  根据品类Id 查找品牌
	 * @param cid
	 * @return
	 */
	public List<Brand> findByCid(Long cid){
		List<Brand> list = brandMapper.findByCid(cid);
		if(CollectionUtils.isEmpty(list)){
		throw new KangLongException(ExceptionType.BRAND_NOT_FOUND);
		}
		return  list;
	}

	/**
	 * 按照id删除品牌
	 */
	@Transactional
	public void deleteById(Long id){
		//删除图片
		Brand brand = this.findById(id);
		int index ;
		String fileName;
		File file = null;
		if(null!= brand){
			//获取路径
			String logoPath = brand.getImage();
			if(StringUtils.isNotBlank(logoPath)){
				index= logoPath.lastIndexOf("/")+1;
				fileName = logoPath.substring(index);
				logger.info("index={},fileName={},path9000={},path9001={}",index,fileName,imageAddr9000,imageAddr9001);
				//删除9000和9001路径下图片
				file = new File(imageAddr9000 ,fileName);
				if(file.exists()) {
					file.delete();
				}
				file = new File(imageAddr9001 ,fileName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		//删除数据库数据
		brandMapper.deleteByPrimaryKey(id) ;
	}


}

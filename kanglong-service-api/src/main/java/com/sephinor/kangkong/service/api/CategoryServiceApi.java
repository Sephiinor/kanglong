package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@FeignClient("business-service")
public interface CategoryServiceApi {

    /**
     *  查询所有品类
     */
    @GetMapping("/category/findAll")
    ResponseEntity<List<Category>> findAll() ;


    /**
     *  查询所有品牌brandId对应的品类
     */
    @GetMapping("/category/findByBrandId")
    ResponseEntity<List<Category>> findByBrandId(@RequestParam("bid")Long bid) ;


    @GetMapping("/category/findTreeCategories")
    ResponseEntity<List<CategoryVO>> findTreeCategories();

}

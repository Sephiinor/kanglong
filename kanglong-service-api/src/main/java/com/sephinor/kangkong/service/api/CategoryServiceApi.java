package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient("business-service")
public interface CategoryServiceApi {

    /**
     *  查询所有品类
     */
    @GetMapping("/category/findAll")
    ResponseEntity<List<Category>> findAll() ;


}

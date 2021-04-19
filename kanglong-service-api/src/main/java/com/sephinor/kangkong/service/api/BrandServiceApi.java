package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("business-service")
public interface BrandServiceApi {

    /**
     *  按照id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/brand/findbyid")
    Brand findById(@RequestParam(value="id")Long id) ;

    /**
     * 分页查询，指定各种条件
     */
    @GetMapping("/brand/paging")
    ResponseEntity<PageResult<Brand>> findPagingAndSortByName(
            @RequestParam(value = "pno", defaultValue = "1") Integer pno,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "name", required = false) String name) ;

    /**
     * 按照品类查询品牌
     */
    @GetMapping("/brand/findByCid")
     ResponseEntity<List<Brand>> findByCid(@RequestParam("cid") Long cid) ;

    /**
     * 按照id集合查询品牌
     */
    @GetMapping("/brand/findByIds")
    ResponseEntity<List<Brand>> findByIds(@RequestParam("ids") Long[] ids) ;

    /**
     *  新增或更新品牌
     */
    @PostMapping("/brand/saveOrUpdate")
    ResponseEntity<String> saveOrUpdateBrand(@RequestBody Brand brand , @RequestParam("cids") Long[] cids) ;

    //删除品牌
    @GetMapping("/brand/deletebyid")
    void deleteById(@RequestParam("id") Long id) ;


    /**
     *  查找所有品牌
     */
    @GetMapping("/brand/findALl")
    List<Brand> findAll() ;
}

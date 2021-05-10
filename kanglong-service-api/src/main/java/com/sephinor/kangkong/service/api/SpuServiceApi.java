package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Spu;
import com.sephinor.common.vo.SpuVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("business-service")
public interface SpuServiceApi {


    //删除品牌
    @GetMapping("/spu/findSpus")
    List<SpuVO> findSpus() ;


    @PostMapping("/spu/saveOrUpdate")
    void saveOrUpdate(@RequestBody Spu spu);

    /**
     *  根据id删除spu
     * @param id
     */
    @GetMapping("/spu/deleteById")
    void deleteById(@RequestParam("spuId") Long id);


    /**
     *  根据id查找spu
     * @param id
     */
    @GetMapping("/spu/findById")
    Spu findById(@RequestParam("id") Long id);
}

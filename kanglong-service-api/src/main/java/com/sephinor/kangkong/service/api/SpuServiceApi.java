package com.sephinor.kangkong.service.api;


import com.sephinor.common.vo.SpuVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("business-service")
public interface SpuServiceApi {


    //删除品牌
    @GetMapping("/spu/findSpus")
    List<SpuVO> findSpus() ;
}

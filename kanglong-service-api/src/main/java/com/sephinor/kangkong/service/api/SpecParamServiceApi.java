package com.sephinor.kangkong.service.api;


import com.sephinor.common.vo.SpecParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("business-service")
public interface SpecParamServiceApi {

    @GetMapping("/specParam/findByCid")
    List<SpecParamVO> findByCid(@RequestParam("cid") Long cid) ;
    
}

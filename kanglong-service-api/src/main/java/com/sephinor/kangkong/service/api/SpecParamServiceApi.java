package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.vo.SpecParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("business-service")
public interface SpecParamServiceApi {

    @GetMapping("/specParam/findByCid")
    List<SpecParamVO> findByCid(@RequestParam("cid") Long cid) ;


    @PostMapping("/specParam/saveOrUpdate")
    void saveOrUpdateParam(@RequestBody SpecParam specParam);
}

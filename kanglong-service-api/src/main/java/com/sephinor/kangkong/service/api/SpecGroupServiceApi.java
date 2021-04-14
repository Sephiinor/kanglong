package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Category;
import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.SpecGroupVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("business-service")
public interface SpecGroupServiceApi {

    /**
     *  根据品类id查找规格参数
     * @param cid
     * @return
     */
    @GetMapping("/specGroup/findByCid")
    List<SpecGroup> findByCid(@RequestParam("cid") Long  cid);

    /**
     *  根据规格组id查找规格参数
     * @param id
     * @return
     */
    @GetMapping("/specGroup/findById")
    SpecGroup findById(@RequestParam("id") Long  id);

    @GetMapping("/specGroup/findGroups")
    List<SpecGroupVO> findGroups();


    @PostMapping("/specGroup/saveOrUpdate")
    void saveOrUpdateGroup(@RequestBody SpecGroup group );

    /**
     *  根据id删除规格组
     * @param id
     */
    @GetMapping("/specGroup/deletebyId")
    void deleteById(@RequestParam("id") Long id) ;
}

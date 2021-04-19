package com.sephinor.kanglong.controller;


import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.SpuVO;
import com.sephinor.kangkong.service.api.BrandServiceApi;
import com.sephinor.kangkong.service.api.CategoryServiceApi;
import com.sephinor.kangkong.service.api.SpuServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/spu")
public class SpuControllerProxy {

    Logger logger = LoggerFactory.getLogger(SpuControllerProxy.class);

    @Autowired
    private SpuServiceApi spuService;

    @Autowired
    private CategoryServiceApi categoryService;

    @Autowired
    private BrandServiceApi brandService;

    /**
     *  商品管理页面入口
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){

        List<CategoryVO> categories = categoryService.findTree().getBody();
        List<SpuVO> spus = spuService.findSpus();
        //所有商品
        model.addAttribute("spus",spus);
        //所有品类
        model.addAttribute("categories",categories);

        return "spu/Manage";
    }


}

package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kangkong.service.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/index")
    public String manage(Model model){
        // 远程查询所有的品牌
        PageResult<Brand> result = brandService.findPagingAndSortByName(1,Integer.MAX_VALUE,"",false,"").getBody();
        //发送UI组件
        model.addAttribute("list",result.getList());

        return "/brand/manage";
    }
}

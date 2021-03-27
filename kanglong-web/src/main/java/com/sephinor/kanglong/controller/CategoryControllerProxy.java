package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.kangkong.service.api.CategoryServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/category")
public class CategoryControllerProxy {

    Logger logger = LoggerFactory.getLogger(CategoryControllerProxy.class);

    @Autowired
    private CategoryServiceApi categoryServiceApi;

    /**
     *  品类管理入口
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        //查询所有品类
        List<CategoryVO> categories = categoryServiceApi.findTreeCategories().getBody();
        //发送UI组件
        model.addAttribute("list",null);
        model.addAttribute("categories",categories);

        return "/category/manage";
    }


    /**
     *  品牌管理查询品类
     */
    @RequestMapping("/findAll")
    public List<Category> findAll(Model model){
        //远程查询所有品牌
        List<Category> all = categoryServiceApi.findAll().getBody();

        return all;

    }

    /**
     *  品牌管理通过品牌Id查询品类
     */
    @RequestMapping("/findbybid")
    @ResponseBody
    public List<Category> findByBrandId(@RequestParam("bid") Long bid){
        //远程查询所有的品牌
        List<Category> list = categoryServiceApi.findByBrandId(bid).getBody() ;
        logger.info("查的{}对应的品类信息为{}",bid,list.toString());
        return list ;
    }

}

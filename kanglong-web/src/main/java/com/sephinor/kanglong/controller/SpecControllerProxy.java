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
@RequestMapping("/spec")
public class SpecControllerProxy {

    Logger logger = LoggerFactory.getLogger(SpecControllerProxy.class);

    @Autowired
    private CategoryServiceApi categoryService;

    /**
     *  规格组管理入口
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        //查询指定分类的分类集合
        List<Category> list= categoryService.findSubList(0L);
        //发送UI组件
        model.addAttribute("list",list);

        return "/spec/manage";
    }


}

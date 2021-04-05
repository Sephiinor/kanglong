package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Category;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kangkong.service.api.CategoryServiceApi;
import org.apache.commons.lang.StringUtils;
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
        List<CategoryVO> categories = categoryServiceApi.findTree().getBody();
        //发送UI组件
        model.addAttribute("list",categories);


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


    /**
     *  保存或更新
     * @param category
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public void saveOrUpdate(Category category) {
        logger.info("【CategoryControllerProxy . saveOrUpdate】的入参为: category:{}",category.toString());

        //叶子节点默认为false
        if(null == category.getLeaf() || "".equals(category.getLeaf())){
            category.setLeaf(false);
        }

        logger.info("保存或更新入参为:"+category.toString());

        categoryServiceApi.saveOrUpdate(category);
    }


    /**
     *  删除品类,不能有子类,不能有关联品牌
     * @param id
     */
    @GetMapping("/deleteById")
    @ResponseBody
    public void deleteById(@RequestParam("id") Long id){
        logger.info("【CategoryControllerProxy.deleteById】的入参为: id:{} =",id);
        categoryServiceApi.deleteById(id);
    }

    /**
     *  品牌管理通过品牌Id查询品类
     */
    @GetMapping("/findById")
    @ResponseBody
    public Category findByCategoryId(@RequestParam("id") Long id){
        logger.info("【CategoryControllerProxy . findByCategoryId】的入参为: id:{}",id);
        return categoryServiceApi.findById(id) ;
    }

}

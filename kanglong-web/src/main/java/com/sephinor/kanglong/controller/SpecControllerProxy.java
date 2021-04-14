package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.SpecGroup;
import com.sephinor.common.entity.SpecParam;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.SpecGroupVO;
import com.sephinor.kangkong.service.api.CategoryServiceApi;
import com.sephinor.kangkong.service.api.SpecGroupServiceApi;
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

    @Autowired
    private SpecGroupServiceApi specGroupService;

    /**
     *  规格组管理入口
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        //查询指定分类的分类集合
        List<CategoryVO> categories= categoryService.findTree().getBody();
        //查询所有商户组
        List<SpecGroupVO> list = specGroupService.findGroups();

        //发送UI组件
        model.addAttribute("list" , list) ;
        model.addAttribute("categories",categories);

        return "spec/groupManage";
    }


    /**
     *  按照品类id查询规格参数
     * @return
     */
    @GetMapping("/findByCid")
    @ResponseBody
    public List<SpecGroup> findByCid(@RequestParam("cid") Long  cid){
       logger.info("【SpecControllerProxy.findByCid】的入参为: cid:{} ",cid);
        return specGroupService.findByCid(cid);

    }

    /**
     *  查询所有规格组,按照品类排序
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public  void  saveOrUpdateGroup( SpecGroup group ){
        logger.info("【SpecControllerProxy.saveOrUpdateGroup】的入参为: group:{} ",group.toString());
        specGroupService.saveOrUpdateGroup(group);
    }


    /**
     *  按照规格组id查询规格参数
     * @return
     */
    @GetMapping("/findByGid")
    @ResponseBody
    public SpecGroup findById(@RequestParam("gid") Long  gid){
        logger.info("【SpecControllerProxy.findById】的入参为: gid:{} ",gid);
        return specGroupService.findById(gid);

    }

    @GetMapping("/deleteGroupByGid")
    @ResponseBody
    public void deleteById(@RequestParam("gid") Long gid){
        logger.info("【SpecControllerProxy.deleteById】的入参为: gid:{} ",gid);

        specGroupService.deleteById(gid);
    }
    
    
}

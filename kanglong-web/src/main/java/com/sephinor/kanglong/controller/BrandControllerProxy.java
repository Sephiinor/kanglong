package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kangkong.service.api.BrandServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/brand")
public class BrandControllerProxy {

    Logger logger = LoggerFactory.getLogger(BrandControllerProxy.class);

    @Autowired
    private BrandServiceApi brandService;

    @RequestMapping("/index")
    public String manage(Model model){
        // 远程查询所有的品牌
        PageResult<Brand> result = brandService.findPagingAndSortByName(1,Integer.MAX_VALUE,"",false,"").getBody();
        //发送UI组件
        model.addAttribute("list",result.getList());

        return "/brand/manage";
    }


    //保存品牌
    @PatchMapping("/save")
    public String save( @RequestBody Brand brand , Long[] cids){
        //插入
        if(null == brand.getId() ){
            logger.info("开始插入品牌:" + brand.toString());
            return brandService.insertBrand(brand, cids).getBody();
        }
        //更新


        return null;
    }


    /**
     *  图片上传
     * @param image
     * @return
     */
    @PostMapping("/upload")
    public String upload( MultipartFile image){
        try {
            //获得原始名称
            String origName = image.getOriginalFilename();
            File destFile = new File("d:/images" , origName) ;
            image.transferTo(destFile);
            System.out.println("上传路径为:"+destFile.getAbsolutePath());
            return destFile.getAbsolutePath() ;
        }catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }

    }
}

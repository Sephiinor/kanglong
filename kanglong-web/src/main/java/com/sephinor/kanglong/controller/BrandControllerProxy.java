package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Brand;
import com.sephinor.common.vo.CategoryVO;
import com.sephinor.common.vo.PageResult;
import com.sephinor.kangkong.service.api.BrandServiceApi;
import com.sephinor.kangkong.service.api.CategoryServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@Controller
@RequestMapping("/brand")
public class BrandControllerProxy {

    Logger logger = LoggerFactory.getLogger(BrandControllerProxy.class);

    @Value("${com.sephinor.image.prefix}")
    private String imagePrefix;
    @Value("${com.sephinor.image.addr9000}")
    private String imageAddr9000;
    @Value("${com.sephinor.image.addr9001}")
    private String imageAddr9001;



    @Autowired
    private BrandServiceApi brandService;

    @Autowired
    private CategoryServiceApi categoryServiceApi;

    @RequestMapping("/index")
    public String manage(Model model){
        // 远程查询所有的品牌
        PageResult<Brand> result = brandService.findPagingAndSortByName(1,Integer.MAX_VALUE,"",false,"").getBody();
        //查询所有品类
        List<CategoryVO> categories = categoryServiceApi.findTree().getBody();
        //发送UI组件
        model.addAttribute("list",result.getList());
        model.addAttribute("categories",categories);

       return "/brand/manage";
    }


    //新增或更新品牌
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Brand brand , Long[] cids){

        logger.info("开始插入品牌:" + brand.toString());
        return brandService.saveOrUpdateBrand(brand, cids).getBody();

    }


    /**
     *  图片上传
     * @param image
     * @return 图片url地址
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile image){

        logger.info("开始上传图片");

        try {
            long time= System.currentTimeMillis();
            //获得原始名称
            String origName= image.getOriginalFilename();
            //文件名
            String fileName = origName.substring(0,origName.lastIndexOf("."));
            //拓展名
            String ext= origName.substring(origName.lastIndexOf("."));

            String newFileName = fileName +"_"+time+ext;


            File file9000 = new File(imageAddr9000 , newFileName);
            File file9001 = new File(imageAddr9001 , newFileName) ;

            copyFile(image.getInputStream(),file9000);
            copyFile(image.getInputStream(),file9001);

            logger.info("文件{}上传成功!",newFileName);

            return imagePrefix+newFileName ;
        }catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }

    }

    /**
     *  复制文件
     * @param in
     * @param dest
     */
    private void copyFile(InputStream in , File dest){

        try {
            FileOutputStream fout = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int len =0;

            while ((len = in.read(buf))!= -1){
                fout.write(buf , 0 ,len);
            }
            fout.close();
            in.close();
        } catch (FileNotFoundException e) {

            logger.info("{}文件未找到",dest);
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("{}文件上传异常",dest);
            e.printStackTrace();
        }


    }

    //删除品牌
    @GetMapping("/deleteById")
    @ResponseBody
    public void deleteById(@RequestParam("id") Long id){
        logger.info("执行删除品牌{}",id);
        brandService.deleteById(id);
    }

    /**
     * 按照id查询Brand
     */
    @GetMapping("/findbyid")
    @ResponseBody
    public Brand findById(@RequestParam("id") Long id){

        return brandService.findById(id) ;
    }



    @GetMapping("/findAll")
    @ResponseBody
    public List<Brand> findAll (){

        return brandService.findAll();
    }

    //按照品类查询品牌
    @GetMapping("/findByCid")
    @ResponseBody
    public List<Brand> findByCid(@RequestParam("cid") Long cid){
        logger.info("【BrandControllerProxy.findByCid】的入参为: cid:{} ",cid);
        return brandService.findByCid(cid).getBody() ;
    }

}

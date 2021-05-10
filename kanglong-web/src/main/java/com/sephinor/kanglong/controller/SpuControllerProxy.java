package com.sephinor.kanglong.controller;


import com.sephinor.common.entity.Sku;
import com.sephinor.common.entity.Spu;
import com.sephinor.common.entity.SpuDetail;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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


    /**
     *  新增或保存
     * @param spu
     * @param spuDetail
     * @param request
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public void saveOrUpdate(Spu spu , SpuDetail spuDetail , HttpServletRequest request){
        //取得所有spu
        Map<String , String[]> map =request.getParameterMap();
        String[] skus = request.getParameterValues("sku");
        List<Sku> list = new ArrayList<Sku>();
        Sku sku = null ;
        Date date = new Date();
        for (String s: skus ) {
            sku = new Sku();
            if(spu.getId() == null){
                sku.setCreateTime(date);
            }
            sku.setLastUpdateTime(date);
            sku.setEnable(getBoolean(map , s+".enable"));
            sku.setId(getLong(map , s+".id"));
            sku.setPrice(getLong(map , s+".price"));
            sku.setTitle(getString(map , s+".title"));
            sku.setOwnSpec(s);
            sku.setStock(getInteger(map,s+".stock"));
            list.add(sku);

        }

        //组装spu对象
        spu.setSpuDetail(spuDetail);
        spu.setSkus(list);
        spuService.saveOrUpdate(spu);

    }


    /**
     *  按照id删除spu
     * @param
     *
     */
    @GetMapping("/deleteById")
    @ResponseBody
    public void  deleteById(@RequestParam("spuid")Long id){
        logger.info("【SpuControllerProxy.deleteById】的入参为: id:{} ",id);
        spuService.deleteById(id);
    }


    //从map中提取布尔值
    private Boolean getBoolean(Map<String ,String[]> map,String key){
        String[] values= map.get(key);
        if(values != null && values.length > 0){
            String str = values[0];
            if(null != str && !"".equals(str.trim())){
                return Boolean.parseBoolean(str);

            }
        }
        return null ;
    }

    //从map中提取Int值
    private Integer getInteger(Map<String ,String[]> map,String key){
        String[] values= map.get(key);
        if(values != null && values.length > 0){
            String str = values[0];
            if(null != str && !"".equals(str.trim())){
                return Integer.parseInt(str);

            }
        }
        return null ;
    }
    //从map中提取Long值
    private Long getLong(Map<String ,String[]> map,String key){
        String[] values= map.get(key);
        if(values != null && values.length > 0){
            String str = values[0];
            if(null != str && !"".equals(str.trim())){
                return Long.parseLong(str);

            }
        }
        return null ;
    }
    //从map中提取Long值
    private String getString(Map<String ,String[]> map,String key){
        String[] values= map.get(key);
        if(values != null && values.length > 0){
            String str = values[0];
            if(null != str && !"".equals(str.trim())){
                return str.trim();

            }
        }
        return null ;
    }



}

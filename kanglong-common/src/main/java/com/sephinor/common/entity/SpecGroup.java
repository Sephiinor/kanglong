package com.sephinor.common.entity;

import lombok.Data;
import java.util.List;

/**
 * 规格组
 * 每个商品除了SKU最基本的信息外，还有很多信息，比如尺寸、厚度、重量、是否含摄像头、像素分
 * 辨率等等，例如如下的某个商品的SKU：
 */
@Data
public class SpecGroup {
    private Long id;                //组id
    private Long cid;               //属于哪个类别id
    private String name;            //组名
    private List<SpecParam> params; //规格参数列表
}

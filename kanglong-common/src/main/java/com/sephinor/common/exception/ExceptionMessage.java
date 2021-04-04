package com.sephinor.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ExceptionMessage {


    public static final String INVALID_FILE_TYPE = "无效文件类型" ;

    public static final String CATEGORY_NOT_FOUND = "商品分类没有找到";

    public static final String SKU_NOT_FOUND	 = "sku没有找到";

    public static final String SPU_DETAIL_NOT_FOUND="商品详情不存在";

    public static final String SPEC_GROUP_NOT_FOUND="商品规格组没有查到";

    public static final String SPEC_PARAM_NOT_FOUND="商品规格参数不存在";

    public static final String ITEM_NOT_FOUND	 = "商品未找到";

    public static final String BRAND_NOT_FOUND	 = "品牌未找到";

    public static final String BRAND_SAVE_ERROR	= "商品保存失败";

    public static final String ITEM_SAVE_ERROR = "新增商品失败";

    public static final String UPLOAD_FILE_ERROR = "文件上传失败";

    public static final String CATEGORY_DELETE_ERROR = "品类删除异常!";

    public static final String CATEGORY_NOT_FOUND_ERROR = "品类不存在!!" ;

/*    // ========= 400 ==========
    INVALID_FILE_TYPE (400,"无效文件类型"),
    CATEGORY_NOT_FOUND (404,"商品分类没有找到"),
    SKU_NOT_FOUND (404,"sku没有找到"),
    SPU_DETAIL_NOT_FOUND(404,"商品详情不存在"),
    SPEC_GROUP_NOT_FOUND(404,"商品规格组没有查到"),
    SPEC_PARAM_NOT_FOUND(404,"商品规格参数不存在"),
    ITEM_NOT_FOUND (404,"商品不存在"),
    BRAND_NOT_FOUND(404,"品牌不存在"),

    // ========= 500 ==========
    BRAND_SAVE_ERROR (500,"商品保存失败"),
    ITEM_SAVE_ERROR (500,"新增商品失败"),
    UPLOAD_FILE_ERROR (500,"文件上传失败"),
    CATEGORY_DELETE_ERROR(500,"品类删除异常");


    private int code;
    private String info;*/

}

package com.sephinor.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionType {

    // ========= 400 ==========
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
    UPLOAD_FILE_ERROR (500,"文件上传失败");

    private int code;
    private String info;

}

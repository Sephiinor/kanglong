package com.kanglong.common.test;

import com.sephinor.common.exception.ExceptionMessage;
import com.sephinor.common.exception.KangLongException;
import org.junit.Test;

/**
 *  测试自定义异常
 */
public class TestKangLongException {

    @Test
    public void t1(){
        KangLongException e = new KangLongException(ExceptionMessage.INVALID_FILE_TYPE);
        System.out.println("异常信息为:"+e.toString());
    }

}

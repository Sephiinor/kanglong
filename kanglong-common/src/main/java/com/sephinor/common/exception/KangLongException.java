package com.sephinor.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  自定义运行时异常
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KangLongException extends RuntimeException{
    private  ExceptionType exceptionType;
}

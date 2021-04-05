package com.sephinor.kangkong.service.api;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("business-service")
public interface SpecParamServiceApi {
    
}

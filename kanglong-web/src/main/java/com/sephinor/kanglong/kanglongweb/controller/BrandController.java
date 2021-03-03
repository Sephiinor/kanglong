package com.sephinor.kanglong.kanglongweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @RequestMapping("/index")
    public String manage(){
        System.out.println("hello ");
        return "/brand/manage";
    }
}

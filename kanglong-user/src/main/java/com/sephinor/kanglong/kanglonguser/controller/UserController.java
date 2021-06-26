package com.sephinor.kanglong.kanglonguser.controller;

import com.sephinor.kanglong.kanglonguser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/check/{data}/{type}")
    public boolean checkData(@PathVariable("data")String data,@PathVariable("type") int type){

        return userService.checkData(data,type);
    }
}

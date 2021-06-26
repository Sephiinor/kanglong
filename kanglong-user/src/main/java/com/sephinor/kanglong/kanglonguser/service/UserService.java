package com.sephinor.kanglong.kanglonguser.service;

import com.sephinor.common.entity.User;
import com.sephinor.kanglong.kanglonguser.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    public boolean  checkData(String data , int type){
        User  user = new User();
        if(type == 1){
            user.setUsername(data);
        }else {
            user.setPhone(data);
        }
        boolean isExist = !(userMapper.selectCount(user) ==0);
        return isExist ;

    }

}

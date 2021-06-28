package com.sephinor.kanglong.kanglonguser.controller;

import com.sephinor.common.entity.User;
import com.sephinor.kanglong.kanglonguser.service.SmsSendService;
import com.sephinor.kanglong.kanglonguser.service.UserService;
import com.sephinor.kanglong.kanglonguser.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SmsSendService smsSendService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${redis.verifyCode.expire.second}")
    private Integer expires;

    private static final String VerifyCodeKey = "verifyCode_tel";

    @GetMapping("/check/{data}/{type}")
    public boolean checkData(@PathVariable("data")String data,@PathVariable("type") int type){

        return userService.checkData(data,type);
    }


    /**
     *  发送验证码给客户端,同时写入redis
     */
    @GetMapping("/verifyCode/{phone}")
    public void sendVerifyCode(@PathVariable("phone") String phone){
        logger.info( "UserController.sendVerifyCode 入参为" + "phone = [" + phone + "]");
        if(redisTemplate.hasKey(VerifyCodeKey+phone)){
            long time =redisTemplate.getExpire(VerifyCodeKey+phone);
            logger.info("短信已发送,请勿重复发起,剩余时间: {}秒",time);
            return;
        }
        //1.生成验证码
        String verifyCode = Helper.randomNum(6);
        logger.info( "UserController.sendVerifyCode 生成的验证码为" + "code = [" + verifyCode + "]");
        //2.发送验证码给客户端
        //smsSendService.sendSms(phone,verifyCode,"","");
        //3.验证码连同phone写入Redis
        redisTemplate.boundValueOps(VerifyCodeKey+ phone).set(verifyCode,expires, TimeUnit.SECONDS);
        logger.info("{}短信验证码{}已存入redis",phone,verifyCode);

    }

    @PostMapping("/register")
    public boolean register(User user, @RequestParam("code")String verifyCode){
        logger.info( "UserController.register 入参为" + "user = [" + user + "], verifyCode = [" + verifyCode + "]");
        
        return userService.register(user, verifyCode);

    }

}

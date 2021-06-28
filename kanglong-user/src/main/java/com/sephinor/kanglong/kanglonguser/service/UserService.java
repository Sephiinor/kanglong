package com.sephinor.kanglong.kanglonguser.service;

import com.sephinor.common.entity.User;
import com.sephinor.kanglong.kanglonguser.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    private StringRedisTemplate redisTemplate;

    private static final String VerifyCodeKey = "verifyCode_tel";

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

    /**
     *  注册用户
     * @return
     */
    public boolean register(User user ,String verifyCode) {
        logger.info( "UserService.register 入参为" + "user = [" + user + "], verifyCode = [" + verifyCode + "]");
        //判断验证码
        String telphone =user.getPhone();
        String redisCode = redisTemplate.boundValueOps(VerifyCodeKey+telphone).get();
        if(!verifyCode.equals(redisCode)){
            logger.info(user+"验证码异常");
            return  false;
        }
        //使用MD5对密码加密
        String MD5pwd  = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(MD5pwd);

        //注册时间
        user.setCreated(new Date());

        boolean result =  userMapper.insert(user) == 1;
        if(result){
            //删除redis中验证码
            redisTemplate.delete(verifyCode+telphone);
            logger.info(verifyCode+telphone+" 已删除");
        }
        return result;

    }
}

package com.sephinor.kanglong.kanglonguser.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  发送短信
 */
@Service
public class SmsSendService {

    private static final Logger logger = LoggerFactory.getLogger(SmsSendService.class);

    //短信发送域名
    @Value("${aliyun.sms.domain}")
    private String domain ;

    @Value("${aliyun.sms.product}")
    private String product ;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.signName}")
    private String signName;

    @Value("${aliyun.sms.verifyCodeTemplate}")
    private String verifyCodeTemplate;
    /**
     *  regionId & endpointName
     */
    @Value("${aliyun.sms.regionId}")
    private  String regionId;


    /**
     * 发送短信
     * @return
     */
    public SendSmsResponse sendSms(String phone , String code , String signName, String template)  {
        //可自主调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout","10000");
        System.setProperty("sun.net.client.defaultConnectTimeout","10000");

        //初始化acsClient ,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        try {
            DefaultProfile.addEndpoint(regionId,regionId,product,domain);
        } catch (ClientException e) {
            logger.info("初始化acsClient失败");
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象 - 具体描述控制台
        SendSmsRequest request=new SendSmsRequest();
        request.setMethod(MethodType.POST);
        //必填,待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短息前面
        request.setSignName(signName);
        //必填:短信模板
        request.setTemplateCode(template);
        //选填:模板中的变量替换Json传
        Map paramMap = new HashMap<>();
        paramMap.put("code",code);
        String codeJson = JSONObject.toJSONString(paramMap);
        System.out.println(codeJson);
        request.setTemplateParam(codeJson);

        //选填 - 上行短信拓展码
        request.setSmsUpExtendCode("90997");

        //选填 outId为提供给业务方拓展字段.最终在短信绘制消息中将此值带会给调用者
        request.setOutId("123456");

        //hint
        SendSmsResponse response = null;
        try {
            response = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            logger.info("acsClient接收响应失败");
            e.printStackTrace();
        }

        return response;

    }

}

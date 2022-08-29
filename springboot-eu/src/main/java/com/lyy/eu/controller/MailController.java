package com.lyy.eu.controller;

import com.lyy.eu.pojo.Member;
import com.lyy.eu.pojo.UsersBo;
import com.lyy.eu.service.MailService;
import com.lyy.eu.service.MemberService;
import com.lyy.eu.util.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
    public RestResponse<String> getCheckCode(@RequestBody UsersBo usersBo){
        Member findemail = memberService.findemail(usersBo.getEmail());

        RestResponse restResponse = new RestResponse();

        if(findemail != null){
            restResponse.setCode(1003);
            restResponse.setMsg("该邮箱已被注册！");
            return restResponse;
        }

        log.info("进入方法getCheckCode:"+usersBo.toString());
        if(redisTemplate.opsForValue().get("checkCode") != null){
            restResponse.setCode(1001);
            restResponse.setMsg("验证码已存在，请注意查收!");
            return restResponse;
        }
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "欢迎来到黑礼服活动策划平台,您的注册验证码为："+checkCode;
        redisTemplate.opsForValue().set("checkCode", checkCode, 120, TimeUnit.SECONDS);



        try {
            mailService.sendSimpleMail(usersBo.getEmail(), "注册验证码", message);
        }catch (Exception e){
            restResponse.setCode(1002);
            restResponse.setData(e);
            return restResponse;
        }
        restResponse.setCode(200);
        restResponse.setData(checkCode);
        return restResponse;
    }

    @RequestMapping(value = "/getupdatepwdCode", method = RequestMethod.POST)
    public RestResponse<String> getupdatepwdCode(@RequestBody UsersBo usersBo){

        Member findemail = memberService.findemail(usersBo.getEmail());

        RestResponse restResponse = new RestResponse();

        if(findemail == null){
            restResponse.setCode(1003);
            restResponse.setMsg("该邮箱不存在！");
            return restResponse;
        }

        log.info("进入方法getCheckCode:"+usersBo.toString());
        if(redisTemplate.opsForValue().get("checkCode") != null){
            System.out.println();
            restResponse.setCode(1001);
            restResponse.setMsg("验证码已存在，请注意查收!");
            return restResponse;
        }
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "欢迎来到黑礼服活动策划平台,您的验证码为："+checkCode;
        redisTemplate.opsForValue().set("checkCode", checkCode, 120, TimeUnit.SECONDS);



        try {
            mailService.sendSimpleMail(usersBo.getEmail(), "注册验证码", message);
        }catch (Exception e){
            restResponse.setCode(1002);
            restResponse.setData(e);
            return restResponse;
        }
        restResponse.setCode(200);
        restResponse.setData(checkCode);
        return restResponse;
    }
}


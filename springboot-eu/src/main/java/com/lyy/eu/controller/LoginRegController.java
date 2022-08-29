package com.lyy.eu.controller;

import com.lyy.eu.pojo.Member;
import com.lyy.eu.service.MemberService;
import com.lyy.eu.util.RestResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginRegController {

    @Resource
    private MemberService memberService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/loginMember")
    public RestResponse<Member> loginMember(String email, String pwd) {
        Member member = memberService.loginMember(email, pwd);
        RestResponse<Member> restResponse = new RestResponse();
        System.out.println(member);
        if (member == null) {
            restResponse.setCode(201);
            restResponse.setMsg("账号不存在");
            return restResponse;
        }
        restResponse.setCode(200);
        restResponse.setMsg("登录成功");
        restResponse.setData(member);
        return restResponse;
    }


    @RequestMapping("/regMember")
    public RestResponse<String>  regMember(Member member, String code) {

        RestResponse restResponse = new RestResponse();
        if(redisTemplate.opsForValue().get("checkCode") == null){
            restResponse.setCode(1001);
            restResponse.setMsg("该验证码已失效");
            return restResponse;
        }

        String checkCode = (String) redisTemplate.opsForValue().get("checkCode");

        if(!checkCode.equals(code)){
            restResponse.setCode(1002);
            restResponse.setMsg("该验证码不正确");
            return restResponse;
        }

        memberService.regMember(member);
        restResponse.setCode(200);
        restResponse.setMsg("注册成功！");

        return restResponse;
    }

}


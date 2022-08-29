package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.MemberService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/findUserByMember")
    public RestResponse findUserByUsername(Integer mid) {

        RestResponse<Member> restResponse = new RestResponse();

        if (memberService.findUserByMember(mid) == null) {
            restResponse.setCode(400);
            restResponse.setMsg("用户未登录!");
            return restResponse;
        }

        restResponse.setCode(200);
        restResponse.setData(memberService.findUserByMember(mid));
        restResponse.setMsg("用户登录成功!");

        return restResponse;
    }

    @RequestMapping("/findMemberSend")
    public PageData findMemberSend (PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(memberService.findTotal(pageInfo));
        pageData.setRows(memberService.findMemberSend(pageInfo));
        return pageData;
    }

    @RequestMapping("/updateMember")
    public RestResponse updateMember(Member member) {
        RestResponse restResponse = new RestResponse();

        if (memberService.updateMember(member)) {
            restResponse.setCode(200);
            restResponse.setMsg("修改用户状态成功");
            return restResponse;
        } else {
            restResponse.setCode(400);
            restResponse.setMsg("修改用户状态失败");
            return restResponse;
        }
    }

    @RequestMapping("/delMember")
    public RestResponse delMember(Member member) {
        RestResponse restResponse = new RestResponse();
        try {
            if (memberService.delMember(member)) {
                restResponse.setCode(200);
                restResponse.setMsg("删除用户成功");
                return restResponse;
            } else {
                restResponse.setCode(400);
                restResponse.setMsg("该用户还未注销，删除用户失败");
                return restResponse;
            }
        }catch (Exception e){
        e.printStackTrace();
    }
    return restResponse;
    }

    @RequestMapping("/updateMemberInfo")
    public Boolean updateMemberInfo(Member member){
        return memberService.updateMemberInfo(member);
    }

    @RequestMapping("/updateHeadImg")
    public Boolean updateHeadImg(Member member, MultipartFile file) throws IOException {

        if (file == null) {
            member.setPhoto("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg");
        } else {
            String filename = file.getOriginalFilename();
            File file1 = new File("G:/daima/IJ/vue-eu/static/img/headImg/" + filename);
            file.transferTo(file1);
            member.setPhoto("/static/img/headImg/" + filename);
        }
        return memberService.updateHeadImg(member);
    }

    @RequestMapping("/updatepwd")
    public RestResponse<String>  updatepwd(Member member, String code) {

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
        memberService.updatepwd(member);
        restResponse.setCode(200);
        restResponse.setMsg("密码修改成功！");
        return restResponse;
    }

}

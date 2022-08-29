package com.lyy.eu.controller;

import com.lyy.eu.pojo.AdminInfo;
import com.lyy.eu.pojo.PageData;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AdminInfoService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping("/findAdminSend")
    public PageData findAdminSend (PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(adminInfoService.findTotal(pageInfo));
        pageData.setRows(adminInfoService.findAdminSend(pageInfo));
        return pageData;
    }


    @RequestMapping("/updateAdmin")
    public RestResponse updateAdmin(AdminInfo adminInfo,HttpSession session){
        RestResponse restResponse = new RestResponse();
        if (session.getAttribute("adminId")==adminInfo.getAid()){
            if (adminInfoService.updateAdmin(adminInfo)){
                restResponse.setCode(200);
                restResponse.setMsg("修改管理员信息成功");
                return restResponse;
            }else{
                restResponse.setCode(400);
                restResponse.setMsg("修改管理员信息失败");
                return restResponse;
            }
        }
        restResponse.setCode(500);
        restResponse.setMsg("您只能修改您自己的信息");
        return restResponse;
    }

    @RequestMapping("/delAdmin")
    public RestResponse delAdmin(AdminInfo adminInfo) {
        RestResponse restResponse = new RestResponse();
        try {
            if (adminInfoService.delAdmin(adminInfo)) {
                restResponse.setCode(200);
                restResponse.setMsg("删除管理员成功");
                return restResponse;
            } else {
                restResponse.setCode(400);
                restResponse.setMsg("该管理员还未注销，删除管理员失败");
                return restResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return restResponse;
    }

    @RequestMapping("/loginAdminInfo")
    public RestResponse<AdminInfo> loginAdminInfo(String tel, String pwd, HttpSession session) {
        //System.out.println("emial-----" + email + "pwd----" + pwd);
        AdminInfo adminInfo = adminInfoService.loginAdminInfo(tel, pwd);
        RestResponse<AdminInfo> restResponse = new RestResponse();
        if (adminInfo == null) {
            restResponse.setCode(201);
            restResponse.setMsg("该管理员不存在不存在");
            return restResponse;
        }
        restResponse.setCode(200);
        restResponse.setMsg("登录成功");
        session.setAttribute("adminId", adminInfo.getAid());
//        System.out.println(session.getAttribute("adminId"));
//        System.out.println(adminInfo.getAid());
        restResponse.setData(adminInfo);
        return restResponse;
    }



    @RequestMapping("/updatePwd")
    public RestResponse updatePwd(AdminInfo adminInfo){
        RestResponse restResponse = new RestResponse();

            if (adminInfoService.updatePwd(adminInfo)){
                restResponse.setCode(200);
                restResponse.setMsg("修改密码成功");
                return restResponse;
            }else{
                restResponse.setCode(400);
                restResponse.setMsg("修改密码失败");
                return restResponse;
            }
        }

    @RequestMapping("/selectAdmin")
    public RestResponse selectAdmin(HttpSession session){
        RestResponse<Object> response = new RestResponse<>();
        if(session.getAttribute("adminId") == null){
            response.setCode(401);
            response.setMsg("用户未登录");
        }else{
            Integer aid = Integer.parseInt(session.getAttribute("adminId").toString());
            response.setCode(200);
            response.setData(adminInfoService.selectAdmin(aid));
        }

        return response;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session){
       session.removeAttribute("adminId");
//        System.out.println(session.getAttribute("adminId"));
    }
}

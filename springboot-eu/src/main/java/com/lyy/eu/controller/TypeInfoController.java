package com.lyy.eu.controller;


import com.lyy.eu.pojo.*;
import com.lyy.eu.service.TypeService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeInfoController {

    @Autowired
    private TypeService typeService;


    @RequestMapping("/findTypeSend")
    public PageData findTypeSend (PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(typeService.findTotal(pageInfo));
        pageData.setRows(typeService.findTypeSend(pageInfo));
        return pageData;
    }

    @RequestMapping("/selectTname")
    public List<TypeInfo> selectTname (){
        return typeService.selectTname();
    }

    @RequestMapping("/addType")
    public RestResponse addType(TypeInfo typeInfo){
        RestResponse restResponse = new RestResponse();

        if (typeService.addType(typeInfo)){
            restResponse.setCode(200);
            restResponse.setMsg("添加类型成功");
            return restResponse;
        }

        restResponse.setCode(400);
        restResponse.setMsg("添加类型失败");
        return restResponse;
    }

    @RequestMapping("/updateType")
    public RestResponse updateType(TypeInfo typeInfo){
        RestResponse restResponse = new RestResponse();

        if(typeService.findGoodByTidSend(typeInfo.getTid()).size() == 0){
            if (typeService.updateType(typeInfo)){
                restResponse.setCode(200);
                restResponse.setMsg("修改类型成功");
                return restResponse;
            }else{
                restResponse.setCode(400);
                restResponse.setMsg("修改类型失败");
                return restResponse;
            }
        }
        restResponse.setCode(500);
        restResponse.setMsg("该类型已被方案绑定");
        return restResponse;

    }

    @RequestMapping("/delType")
    public RestResponse delType(TypeInfo typeInfo) {
        RestResponse restResponse = new RestResponse();

        try {
            if (typeService.findGoodByTidSend(typeInfo.getTid()).size() == 0) {
                if (typeService.delType(typeInfo)) {
                    restResponse.setCode(200);
                    restResponse.setMsg("删除场地类型成功");
                    return restResponse;
                } else {
                    restResponse.setCode(400);
                    restResponse.setMsg("删除场地类型失败");
                    return restResponse;
                }
            }
            restResponse.setCode(500);
            restResponse.setMsg("该类型已被方案绑定");
            return restResponse;
        }catch (Exception e){
            e.printStackTrace();

        }
        return restResponse;
    }
}


package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.AddressService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressInfoController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/findAddressSend")
    public PageData findAddressSend (PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(addressService.findTotal(pageInfo));
        pageData.setRows(addressService.findAddressSend(pageInfo));
        return pageData;
    }

    @RequestMapping("/findGoodByAidSend")
    public List<GoodsInfo> findGoodByAidSend (Integer aid){
        List<GoodsInfo> list=addressService.findGoodByAidSend(aid);
        if(list==null){
            return  null;
        }
        return list;

    }

    @RequestMapping("/selectAname")
    public List<AddressInfo> selectAname (PageInfo pageInfo){
        return addressService.selectAname(pageInfo);
    }

    @RequestMapping("/addAddress")
    public RestResponse addAddress(AddressInfo addressInfo){
        RestResponse restResponse = new RestResponse();

        if (addressService.addAddress(addressInfo)){
            restResponse.setCode(200);
            restResponse.setMsg("添加场地类型成功");
            return restResponse;
        }

        restResponse.setCode(400);
        restResponse.setMsg("添加场地类型失败");
        return restResponse;
    }

    @RequestMapping("/updateAddress")
    public RestResponse updateAddress(AddressInfo addressInfo) {
        RestResponse restResponse = new RestResponse();

        if (addressService.findGoodByAidSend(addressInfo.getAid()).size() == 0) {
            if (addressService.updateAddress(addressInfo)) {
                restResponse.setCode(200);
                restResponse.setMsg("修改场地类型成功");
                return restResponse;
            } else {
                restResponse.setCode(400);
                restResponse.setMsg("修改场地类型失败");
                return restResponse;
            }
        }
        restResponse.setCode(500);
        restResponse.setMsg("该场地类型已被方案绑定");
        return restResponse;
    }

    @RequestMapping("/delAddress")
    public RestResponse delAddress(AddressInfo addressInfo) {
        RestResponse restResponse = new RestResponse();

        try{
            if (addressService.findGoodByAidSend(addressInfo.getAid()).size() == 0) {
                if (addressService.delAddress(addressInfo)) {
                    restResponse.setCode(200);
                    restResponse.setMsg("删除场地类型成功");
                    return restResponse;
                }else{
                    restResponse.setCode(400);
                    restResponse.setMsg("删除场地类型失败");
                    return restResponse;
                }
            }
            restResponse.setCode(500);
            restResponse.setMsg("该场地类型已被方案绑定");
            return restResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return restResponse;
    }

}

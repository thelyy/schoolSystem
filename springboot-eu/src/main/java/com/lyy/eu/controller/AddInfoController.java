package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.AddInfoService;
import com.lyy.eu.service.AtimeInfoService;
import com.lyy.eu.util.RestResponse;
import com.lyy.eu.util.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AddInfoController {

    @Resource
    private AddInfoService addInfoService;

    @Resource
    private AtimeInfoService atimeInfoService;

    @RequestMapping("/selectAddInfo")
    public List<AddInfo> selectAddInfo(AtimeInfo atimeInfo){
        AtimeInfo atids = atimeInfoService.selectAtid(atimeInfo);
        return addInfoService.selectAddInfo(atids.getAtid());
    }
    @RequestMapping("/findAlladd")
    public List<AddInfo> findAlladd(AddInfo addInfo){
        return addInfoService.findAlladd(addInfo);
    }

    @RequestMapping("/findAddinfoEnd")
    public PageData findAddinfoEnd(PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(addInfoService.findTotal(pageInfo));
        pageData.setRows(addInfoService.findAddinfoEnd(pageInfo));
        return pageData;
    }

    @RequestMapping("/addAdd")
    public RestResponse addAdd(AddInfo addInfo){
        RestResponse restResponse = new RestResponse();

        if (addInfoService.addAdd(addInfo)){
            restResponse.setCode(200);
            restResponse.setMsg("添加成功");
            return restResponse;
        }
        restResponse.setCode(400);
        restResponse.setMsg("添加失败");
        return restResponse;
    }

    @RequestMapping("/updateAdd")
    public RestResponse updateAdd(AddInfo addInfo) {
        RestResponse restResponse = new RestResponse();

        if (addInfoService.findStatus(addInfo) != null) {
            if (addInfoService.updateAdd(addInfo)) {
                restResponse.setCode(200);
                restResponse.setMsg("修改成功");
                return restResponse;
            } else {
                restResponse.setCode(400);
                restResponse.setMsg("修改失败");
                return restResponse;
            }
        }
        restResponse.setCode(500);
        restResponse.setMsg("该时间已预定");
        return restResponse;
    }
    @RequestMapping("/delAdd")
    public ResultVO delAdd(Integer aaid){
        int result=addInfoService.delAdd(aaid);
        if (result>0){
            return new ResultVO(200,"成功",result);
        }
        return new ResultVO(500,"失败",result);
    }
//    @RequestMapping("/delAddress")
//    public RestResponse delAddress(AddressInfo addressInfo) {
//        RestResponse restResponse = new RestResponse();
//
//        try{
//            if (addressService.findGoodByAidSend(addressInfo.getAid()).size() == 0) {
//                if (addressService.delAddress(addressInfo)) {
//                    restResponse.setCode(200);
//                    restResponse.setMsg("删除场地类型成功");
//                    return restResponse;
//                }else{
//                    restResponse.setCode(400);
//                    restResponse.setMsg("删除场地类型失败");
//                    return restResponse;
//                }
//            }
//            restResponse.setCode(500);
//            restResponse.setMsg("该场地类型已被方案绑定");
//            return restResponse;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return restResponse;
//    }
}

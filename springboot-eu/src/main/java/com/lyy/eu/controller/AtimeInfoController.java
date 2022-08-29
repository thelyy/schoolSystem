package com.lyy.eu.controller;

import com.lyy.eu.pojo.AddInfo;
import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.PageData;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AddInfoService;
import com.lyy.eu.service.AtimeInfoService;
import com.lyy.eu.util.RestResponse;
import com.lyy.eu.util.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AtimeInfoController {

    @Resource
    private AtimeInfoService atimeInfoService;

    @Resource
    private AddInfoService addInfoService;

    @RequestMapping("/selectAtime")
    public List<AtimeInfo> selectAtime(){
        return atimeInfoService.selectAtime();
    }

    @RequestMapping("/findAtimeEnd")
    public PageData findAtimeEnd(PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(atimeInfoService.findTotal(pageInfo));
        pageData.setRows(atimeInfoService.findAtimeEnd(pageInfo));
        return pageData;
    }

    @RequestMapping("/addAtime")
    public ResultVO addAdd(AtimeInfo atimeInfo){
        int result=atimeInfoService.addAtime(atimeInfo);
        if (result>0){
            return new ResultVO(200,"成功",result);
        }
        return new ResultVO(500,"失败",result);
    }
    @RequestMapping("/findAtime")
    public List<AtimeInfo> findAtime(Integer atid){
        List<AtimeInfo> list=atimeInfoService.findAtime(atid);
        if (list==null){
            return null;
        }
        return list;
    }
    @RequestMapping("/delAtime")
    public ResultVO delAtime(Integer atid){

        int result=atimeInfoService.delAtime(atid);

        if (result>0){
            return new ResultVO(200,"成功",result);
        }
        return new ResultVO(500,"失败",result);
    }

//    @RequestMapping("/updateAtime")
//    public RestResponse updateAdd(AtimeInfo atimeInfo) {
//        RestResponse restResponse = new RestResponse();
//        AddInfo addInfo =new AddInfo();
//        if (addInfoService.findStatus(addInfo) != null) {
//            if (atimeInfoService.updateAtime(atimeInfo)) {
//                restResponse.setCode(200);
//                restResponse.setMsg("修改成功");
//                return restResponse;
//            } else {
//                restResponse.setCode(400);
//                restResponse.setMsg("修改失败");
//                return restResponse;
//            }
//        }
//        restResponse.setCode(500);
//        restResponse.setMsg("该时间已预定");
//        return restResponse;
//    }

//    @RequestMapping("/delAtime")
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

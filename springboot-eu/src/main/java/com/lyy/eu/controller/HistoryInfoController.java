package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.HistoryInfoService;
import com.lyy.eu.service.LoveInfoService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryInfoController {

    @Autowired
    private HistoryInfoService historyInfoService;

    @Autowired
    private LoveInfoService loveInfoService;

    @RequestMapping("/addHistory")
    public RestResponse addHistory (Histroyinfo histroyinfo){
        RestResponse restResponse = new RestResponse();
        if (historyInfoService.findHistoryStatus(histroyinfo) == null){
            if(historyInfoService.addHistory(histroyinfo)){
                restResponse.setCode(200);
                restResponse.setMsg("添加足迹成功!");
            }else{
                restResponse.setCode(400);
                restResponse.setMsg("添加足迹失败!");
            }
            return restResponse;
        }

        if(historyInfoService.delHistory(histroyinfo)){
            restResponse.setCode(200);
            restResponse.setMsg("删除足迹成功!");
        }else{
            restResponse.setCode(400);
            restResponse.setMsg("删除足迹失败!");
            return restResponse;
        }
        if(historyInfoService.addHistory(histroyinfo)){
            restResponse.setCode(201);
            restResponse.setMsg("添加足迹成功!");
        }else{
            restResponse.setCode(401);
            restResponse.setMsg("添加足迹失败!");
        }
        return restResponse;
    }



    @RequestMapping("/findHistory")
    public GoodsPage findHistory(PageInfo pageInfo, Integer mid){
        LovesInfo lovesInfo = new LovesInfo();
        lovesInfo.setMid(mid);
        GoodsPage goodsPage = new GoodsPage();
        List<GoodsInfo> goodsInfo = historyInfoService.findHistory(pageInfo);
        for(GoodsInfo goodsInfo1 : goodsInfo){
            lovesInfo.setGid(goodsInfo1.getGid());
            if(loveInfoService.findLoveStatus(lovesInfo) != null){
                goodsInfo1.setIsCollect(1);
            }
        }
        Integer historytotal = historyInfoService.findHistoryTotal(pageInfo);
        goodsPage.setHistorytotal(historytotal);
        goodsPage.setGoodsInfoList(goodsInfo);
        return goodsPage;
    }

}

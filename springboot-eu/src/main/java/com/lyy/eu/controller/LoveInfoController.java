package com.lyy.eu.controller;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.GoodsPage;
import com.lyy.eu.pojo.LovesInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.LoveInfoService;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoveInfoController {

    @Autowired
    private LoveInfoService loveInfoService;

    @RequestMapping("/findLoveAll")
    public List<LovesInfo> findLoveAll(LovesInfo lovesInfo) {
          return loveInfoService.findLoveAll(lovesInfo);

    }


    @RequestMapping("/findLoveGoods")
    public GoodsPage findLoveGoods(PageInfo pageInfo,Integer mid){
        GoodsPage goodsPage = new GoodsPage();

        LovesInfo lovesInfo = new LovesInfo();
        lovesInfo.setMid(mid);

        List<GoodsInfo> goodsInfo = loveInfoService.findLoveGoods(pageInfo);
        for(GoodsInfo goodsInfo1 : goodsInfo){
            lovesInfo.setGid(goodsInfo1.getGid());
            if(loveInfoService.findLoveStatus(lovesInfo) != null){
                goodsInfo1.setIsCollect(1);
            }
        }
        Integer lovetotal = loveInfoService.findLoveTotal(pageInfo);
        goodsPage.setGoodsInfoList(goodsInfo);
        goodsPage.setLovetotal(lovetotal);
        return goodsPage;
    }

    @RequestMapping("/addLoveGoods")
    public RestResponse addLoveGoods(LovesInfo lovesInfo){
        RestResponse restResponse = new RestResponse();

        if (!loveInfoService.addLoveGoods(lovesInfo)){
            restResponse.setCode(400);
            restResponse.setMsg("收藏失败");
            return restResponse;
        }

        restResponse.setCode(200);
        restResponse.setMsg("收藏成功");
        return restResponse;
    }

    @RequestMapping("/delLoveGoods")
    public RestResponse delLoveGoods(LovesInfo lovesInfo){
        RestResponse restResponse = new RestResponse();

        if (loveInfoService.delLoveGoods(lovesInfo)){
            restResponse.setCode(200);
            restResponse.setMsg("取消收藏成功");
            return restResponse;
        }

        restResponse.setCode(400);
        restResponse.setMsg("取消收藏失败");
        return restResponse;
    }

    @RequestMapping("/findLoveStatus")
    public RestResponse findLoveStatus(LovesInfo lovesInfo){

        RestResponse restResponse = new RestResponse();
        if (loveInfoService.findLoveStatus(lovesInfo) == null){
            restResponse.setCode(200);
            restResponse.setMsg("");
            return restResponse;
        }

        restResponse.setCode(400);
        restResponse.setMsg("该商品已收藏");
        return restResponse;
    }
}

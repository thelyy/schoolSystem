package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.AddInfoService;
import com.lyy.eu.service.GoodsInfoService;
import com.lyy.eu.service.OrderInfoService;
import com.lyy.eu.util.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private AddInfoService addInfoService;

    @RequestMapping("/selectAll")
    public GoodsPage selectAll(PageInfo pageInfo,Integer mid){
        GoodsPage goodsPage = new GoodsPage();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMid(mid);
        Integer ordertotal = orderInfoService.findTotal(pageInfo);
        goodsPage.setOrdertotal(ordertotal);
        goodsPage.setOrderInfoList(orderInfoService.selectAll(pageInfo));
        return goodsPage;

    }

    @RequestMapping("/selectAllend")
    public PageData selectAllend(PageInfo pageInfo){
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(orderInfoService.findTotal(pageInfo));
        pageData.setRows(orderInfoService.selectAllend(pageInfo));
        return pageData;
    }

    @RequestMapping("/addOrder")
    public RestResponse addOrder(OrderInfo orderInfo){
        RestResponse restResponse = new RestResponse();
        GoodsInfo goodsInfo = goodsInfoService.findPlans(orderInfo.getGid());
        orderInfo.setAid(goodsInfo.getAid());
        if (!orderInfoService.addOrder(orderInfo)){
            restResponse.setCode(400);
            restResponse.setMsg("预约失败");
            return restResponse;
        }
        AddInfo addInfo = new AddInfo();
        addInfo.setAaid(orderInfo.getAaid());
        addInfoService.updateStatus(addInfo);
        restResponse.setCode(200);
        restResponse.setMsg("预约成功");
        return restResponse;
    }
}

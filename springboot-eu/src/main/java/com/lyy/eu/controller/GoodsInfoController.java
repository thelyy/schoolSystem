package com.lyy.eu.controller;

import com.lyy.eu.pojo.*;
import com.lyy.eu.service.GoodsInfoService;
import com.lyy.eu.service.LoveInfoService;
import com.lyy.eu.util.ImageUtil;
import com.lyy.eu.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private LoveInfoService loveInfoService;

    @RequestMapping("/findByPage")
    public GoodsPage findByPage(PageInfo pageInfo, Integer mid) {
        GoodsPage goodPage = new GoodsPage();
        if(pageInfo.getPage() == null && pageInfo.getRows() == null){
            pageInfo.setPage(0);
            pageInfo.setRows(8);
        }

        List<GoodsInfo> goodsInfo =  goodsInfoService.findByPage(pageInfo);
        if(mid != null){
            LovesInfo lovesInfo = new LovesInfo();
            lovesInfo.setMid(mid);
            for(GoodsInfo goodsInfo1 : goodsInfo){
                lovesInfo.setGid(goodsInfo1.getGid());
                if(loveInfoService.findLoveStatus(lovesInfo) != null){
                    goodsInfo1.setIsCollect(1);
                }
            }
        }
        Integer total = goodsInfoService.findTotal(pageInfo);
        goodPage.setGoodsInfoList(goodsInfo);
        goodPage.setTotal(total);
        return goodPage;

    }

    @RequestMapping("/findGoodsByTA")
    public GoodsPage findGoodsByTA(PageInfo pageInfo){
        //System.out.println(pageInfo);
        if(pageInfo.getPage() == null && pageInfo.getRows() == null){
            pageInfo.setPage(0);
            pageInfo.setRows(8);
        }

        GoodsPage goodsPage = new GoodsPage();
        goodsPage.setGoodsInfoList(goodsInfoService.findGoodsByTA(pageInfo));
        goodsPage.setTotal(goodsInfoService.findTotal(pageInfo));
        return goodsPage;
    }

    @RequestMapping("/findAll")
    public List<GoodsInfo>  findAll(PageInfo pageInfo){
        return goodsInfoService.findAll(pageInfo);
    }
    @RequestMapping("/findGoodsBySearch")
    public GoodsPage findGoodsBySearch(PageInfo pageInfo){
        if (pageInfo.getPage() == null && pageInfo.getRows() == null){
            pageInfo.setPage(0);
            pageInfo.setRows(8);
        }

        GoodsPage goodsPage = new GoodsPage();
        goodsPage.setGoodsInfoList(goodsInfoService.findGoodsBySearch(pageInfo));
        goodsPage.setTotal(goodsInfoService.findTotal(pageInfo));
        return goodsPage;
    }

    @RequestMapping("/findGoodsDetail")
    public GoodsInfo findGoodsDetail(Integer gid, Integer mid){
        GoodsInfo goodsDetail = goodsInfoService.findGoodsDetail(gid);

        if(loveInfoService.findLoveStatus(new LovesInfo(mid, gid)) == null){
            goodsDetail.setIsCollect(0);
        }else{
            goodsDetail.setIsCollect(1);
        }
        return goodsDetail;

    }

    @RequestMapping("/findGoodSend")
    public PageData findGoodSend(PageInfo pageInfo) {
        //System.out.println(pageInfo);
        PageData pageData = new PageData();
        pageInfo.setPage((pageInfo.getPage() - 1) * pageInfo.getRows());
        pageData.setTotal(goodsInfoService.findTotal(pageInfo));
        pageData.setRows(goodsInfoService.findGoodSend(pageInfo));
        return pageData;

    }

    @RequestMapping("/findGoodsBySearchSend")
    public PageData findGoodsBySearchSend(PageInfo pageInfo) {
        //System.out.println(pageInfo);
        PageData pageData = new PageData();
        pageData.setTotal(goodsInfoService.findTotal(pageInfo));
        pageData.setRows(goodsInfoService.findGoodsBySearchSend(pageInfo));
        return pageData;

    }

    @RequestMapping("/findPlans")
    public GoodsInfo findPlans (Integer gid){

        return goodsInfoService.findPlans(gid);

    }

    @RequestMapping("/addGoods")
    public RestResponse addGoods(GoodsInfo goodsInfo, MultipartFile pic) {

        RestResponse restResponse = new RestResponse();
        //获取后缀
        String suffixName = ImageUtil.getImagePath(pic);
        //获取新的文件名
        String newFileName = ImageUtil.getNewFileName(suffixName);
        //保存图片
        File file = new File(ImageUtil.getNewImagePath(newFileName));
        boolean state = ImageUtil.saveImage(pic, file);
        if (state) {
            //图片保存成功
            //设置图片到对象
            goodsInfo.setPics("/static/img/product/" + newFileName);
            //保存对象
            if (goodsInfoService.addGoods(goodsInfo)) {
                System.out.println(goodsInfo);
                restResponse.setCode(200);
                restResponse.setMsg("图片存成功");
                return restResponse;
            }
            }else {
                restResponse.setCode(400);
                restResponse.setMsg("图片存失败");
                return restResponse;
            }
        return restResponse;

    }


}

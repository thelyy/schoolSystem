package com.lyy.eu.service;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.LovesInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface LoveInfoService {
    Integer findLoveTotal(PageInfo pageInfo);

    List<GoodsInfo> findLoveGoods(PageInfo pageInfo);

    boolean addLoveGoods(LovesInfo lovesInfo);

    boolean delLoveGoods(LovesInfo lovesInfo);

    LovesInfo findLoveStatus(LovesInfo lovesInfo);

    List<LovesInfo> findLoveAll(LovesInfo lovesInfo);

}

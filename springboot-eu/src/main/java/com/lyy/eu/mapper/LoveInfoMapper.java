package com.lyy.eu.mapper;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.LovesInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface LoveInfoMapper {

    Integer findLoveTotal(PageInfo pageInfo);

    List<GoodsInfo> findLoveGoods(PageInfo pageInfo);

    boolean addLoveGoods(LovesInfo lovesInfo);

    boolean delLoveGoods(LovesInfo lovesInfo);

    LovesInfo findLoveStatus(LovesInfo lovesInfo);

    List<LovesInfo> findLoveAll(LovesInfo lovesInfo);
}

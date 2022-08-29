package com.lyy.eu.mapper;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface GoodsInfoMapper {

    List<GoodsInfo> findByPage(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    List<GoodsInfo> findGoodsByTA(PageInfo pageInfo);

    List<GoodsInfo> findGoodsBySearch(PageInfo pageInfo);

    GoodsInfo findGoodsDetail(Integer gid);

    GoodsInfo findPlans(Integer gid);

    List<GoodsInfo> findGoodSend(PageInfo pageInfo);

    List<GoodsInfo> findGoodsBySearchSend(PageInfo pageInfo);

    boolean addGoods(GoodsInfo goodsInfo);

    List<GoodsInfo> findAll(PageInfo pageInfo);

}

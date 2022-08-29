package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.GoodsInfoMapper;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired(required = false)
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> findByPage(PageInfo pageInfo) {
        return goodsInfoMapper.findByPage(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return goodsInfoMapper.findTotal(pageInfo);
    }

    @Override
    public List<GoodsInfo> findGoodsByTA(PageInfo pageInfo) {
        return goodsInfoMapper.findGoodsByTA(pageInfo);
    }

    @Override
    public List<GoodsInfo> findGoodsBySearch(PageInfo pageInfo) {
        return goodsInfoMapper.findGoodsBySearch(pageInfo);
    }

    @Override
    public GoodsInfo findGoodsDetail(Integer gid) {
        return goodsInfoMapper.findGoodsDetail(gid);
    }

    @Override
    public List<GoodsInfo> findGoodSend(PageInfo pageInfo) {
        return goodsInfoMapper.findGoodSend(pageInfo);
    }

    @Override
    public List<GoodsInfo> findGoodsBySearchSend(PageInfo pageInfo) {
        return goodsInfoMapper.findGoodsBySearchSend(pageInfo);
    }

    @Override
    public boolean addGoods(GoodsInfo goodsInfo) {
        return goodsInfoMapper.addGoods(goodsInfo);
    }

    @Override
    public List<GoodsInfo> findAll(PageInfo pageInfo) {
        return goodsInfoMapper.findAll(pageInfo);
    }

    @Override
    public GoodsInfo findPlans(Integer gid) {
        return goodsInfoMapper.findPlans(gid);
    }


}

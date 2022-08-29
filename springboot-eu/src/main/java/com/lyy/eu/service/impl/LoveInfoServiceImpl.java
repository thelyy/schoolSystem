package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.LoveInfoMapper;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.LovesInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.LoveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoveInfoServiceImpl implements LoveInfoService {

    @Autowired(required = false)
    private LoveInfoMapper loveInfoMapper;

    @Override
    public Integer findLoveTotal(PageInfo pageInfo) {
        return loveInfoMapper.findLoveTotal(pageInfo);
    }

    @Override
    public List<GoodsInfo> findLoveGoods(PageInfo pageInfo) {
        return loveInfoMapper.findLoveGoods(pageInfo);
    }

    @Override
    public boolean addLoveGoods(LovesInfo lovesInfo) {
        return loveInfoMapper.addLoveGoods(lovesInfo);
    }

    @Override
    public boolean delLoveGoods(LovesInfo lovesInfo) {
        return loveInfoMapper.delLoveGoods(lovesInfo);
    }

    @Override
    public LovesInfo findLoveStatus(LovesInfo lovesInfo) {
        return loveInfoMapper.findLoveStatus(lovesInfo);
    }

    @Override
    public List<LovesInfo> findLoveAll(LovesInfo lovesInfo) {
        return loveInfoMapper.findLoveAll(lovesInfo);
    }


}

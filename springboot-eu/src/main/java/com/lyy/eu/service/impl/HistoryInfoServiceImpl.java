package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.HistoryInfoMapper;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.Histroyinfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.HistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryInfoServiceImpl implements HistoryInfoService {

    @Autowired(required = false)
    private HistoryInfoMapper historyInfoMapper;

    @Override
    public boolean addHistory(Histroyinfo histroyinfo) {
        return historyInfoMapper.addHistory(histroyinfo);
    }

    @Override
    public boolean delHistory(Histroyinfo histroyinfo) {
        return historyInfoMapper.delHistory(histroyinfo);
    }

    @Override
    public Histroyinfo findHistoryStatus(Histroyinfo histroyinfo) {
        return historyInfoMapper.findHistoryStatus(histroyinfo);
    }

    @Override
    public List<GoodsInfo> findHistory(PageInfo pageInfo) {
        return historyInfoMapper.findHistory(pageInfo);
    }

    @Override
    public Integer findHistoryTotal(PageInfo pageInfo) {
        return historyInfoMapper.findHistoryTotal(pageInfo);
    }
}

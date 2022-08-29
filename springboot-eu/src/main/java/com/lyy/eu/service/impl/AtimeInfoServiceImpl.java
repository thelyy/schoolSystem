package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.AtimeInfoMapper;
import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AtimeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AtimeInfoServiceImpl implements AtimeInfoService {

    @Resource
    private AtimeInfoMapper atimeInfoMapper;

    @Override
    public List<AtimeInfo> selectAtime() {
        return atimeInfoMapper.selectAtime();
    }

    @Override
    public AtimeInfo selectAtid(AtimeInfo atimeInfo) {
        return atimeInfoMapper.selectAtid(atimeInfo);
    }

    @Override
    public List<AtimeInfo> findAtimeEnd(PageInfo pageInfo) {
        return atimeInfoMapper.findAtimeEnd(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return atimeInfoMapper.findTotal(pageInfo);
    }

    @Override
    public int addAtime(AtimeInfo atimeInfo) {
        atimeInfoMapper.addAtime(atimeInfo);
        return atimeInfo.getAtid();
    }

    @Override
    public int delAtime(Integer atid) {
        return atimeInfoMapper.delAtime(atid);
    }

    @Override
    public List<AtimeInfo> findAtime(Integer atid) {
        return atimeInfoMapper.findAtime(atid);
    }

}

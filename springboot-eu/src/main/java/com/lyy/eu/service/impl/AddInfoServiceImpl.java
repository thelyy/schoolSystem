package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.AddInfoMapper;
import com.lyy.eu.pojo.AddInfo;
import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AddInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddInfoServiceImpl implements AddInfoService {

    @Resource
    private AddInfoMapper addInfoMapper;


    @Override
    public List<AddInfo> selectAddInfo(Integer atid) {
        return addInfoMapper.selectAddInfo(atid);
    }


    @Override
    public List<AddInfo> findAddinfoEnd(PageInfo pageInfo) {
        return addInfoMapper.findAddinfoEnd(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return addInfoMapper.findTotal(pageInfo);
    }

    @Override
    public boolean addAdd(AddInfo addInfo) {
        return addInfoMapper.addAdd(addInfo);
    }

    @Override
    public boolean updateAdd(AddInfo addInfo) {
        return addInfoMapper.updateAdd(addInfo);
    }

    @Override
    public int delAdd(Integer aaid) {
        return addInfoMapper.delAdd(aaid);
    }

    @Override
    public AddInfo findStatus(AddInfo addInfo) {
        return addInfoMapper.findStatus(addInfo);
    }

    @Override
    public List<AddInfo> findAlladd(AddInfo addInfo) {
        return addInfoMapper.findAlladd(addInfo);
    }

    @Override
    public boolean updateStatus(AddInfo addInfo) {
        return addInfoMapper.updateStatus(addInfo);
    }

}

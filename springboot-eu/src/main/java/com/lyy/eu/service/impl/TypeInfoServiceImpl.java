package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.TypeMapper;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.pojo.TypeInfo;
import com.lyy.eu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeInfoServiceImpl implements TypeService {

    @Autowired(required = false)
    private TypeMapper typeMapper;


    @Override
    public List<TypeInfo> selectTname() {
        return typeMapper.selectTname();
    }

    @Override
    public boolean addType(TypeInfo typeInfo) {
        return typeMapper.addType(typeInfo);
    }

    @Override
    public boolean updateType(TypeInfo typeInfo) {
        return typeMapper.updateType(typeInfo);
    }

    @Override
    public List<GoodsInfo> findGoodByTidSend(Integer tid) {
        return typeMapper.findGoodByTidSend(tid);
    }

    @Override
    public boolean delType(TypeInfo typeInfo) {
        return typeMapper.delType(typeInfo);
    }

    @Override
    public List<TypeInfo> findTypeSend(PageInfo pageInfo) {
        return typeMapper.findTypeSend(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return typeMapper.findTotal(pageInfo);
    }
}

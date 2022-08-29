package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.AddressMapper;
import com.lyy.eu.pojo.AddressInfo;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressInfoServiceImpl implements AddressService {

    @Autowired(required = false)
    private AddressMapper addressMapper;


    @Override
    public List<AddressInfo> selectAname(PageInfo pageInfo) {
        return addressMapper.selectAname(pageInfo);
    }

    @Override
    public boolean addAddress(AddressInfo addressInfo) {
        return addressMapper.addAddress(addressInfo);
    }

    @Override
    public boolean updateAddress(AddressInfo addressInfo) {
        return addressMapper.updateAddress(addressInfo);
    }

    @Override
    public List<GoodsInfo> findGoodByAidSend(Integer aid) {
        return addressMapper.findGoodByAidSend(aid);
    }

    @Override
    public boolean delAddress(AddressInfo addressInfo) {
        return addressMapper.delAddress(addressInfo);
    }

    @Override
    public List<AddressInfo> findAddressSend(PageInfo pageInfo) {
        return addressMapper.findAddressSend(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return addressMapper.findTotal(pageInfo);
    }
}

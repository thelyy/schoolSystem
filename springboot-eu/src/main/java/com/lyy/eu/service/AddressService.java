package com.lyy.eu.service;

import com.lyy.eu.pojo.AddressInfo;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface AddressService {
    List<AddressInfo> selectAname(PageInfo pageInfo);

    boolean addAddress(AddressInfo addressInfo);

    boolean updateAddress(AddressInfo addressInfo);

    List<GoodsInfo> findGoodByAidSend(Integer aid);

    boolean delAddress(AddressInfo addressInfo);

    List<AddressInfo> findAddressSend(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);
}

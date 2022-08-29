package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.OrderInfoMapper;
import com.lyy.eu.pojo.OrderInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;


    @Override
    public List<OrderInfo> selectAll(PageInfo pageInfo) {
        return orderInfoMapper.selectAll(pageInfo);
    }

    @Override
    public List<OrderInfo> selectAllend(PageInfo pageInfo) {
        return orderInfoMapper.selectAllend(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return orderInfoMapper.findTotal(pageInfo);
    }

    @Override
    public boolean addOrder(OrderInfo orderInfo) {
        return orderInfoMapper.addOrder(orderInfo);
    }
}

package com.lyy.eu.service;

import com.lyy.eu.pojo.OrderInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface OrderInfoService {

    List<OrderInfo> selectAll (PageInfo pageInfo);

    List<OrderInfo> selectAllend (PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    boolean addOrder(OrderInfo orderInfo);
}

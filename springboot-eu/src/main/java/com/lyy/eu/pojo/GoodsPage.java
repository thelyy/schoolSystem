package com.lyy.eu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class GoodsPage {

    private List<GoodsInfo> goodsInfoList;

    private List<AddressInfo> addressInfoList;

    private List<TypeInfo> typeInfoList;

    private List<OrderInfo> orderInfoList;

    private Integer total;

    private Integer lovetotal;

    private Integer historytotal;

    private Integer ordertotal;
}

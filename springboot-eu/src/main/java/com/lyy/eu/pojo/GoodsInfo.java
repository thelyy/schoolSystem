package com.lyy.eu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {

    private Integer gid; //商品id

    private String gname; //商品名称

    private String pics; //商品图片

    private String detail; //商品详情

    private Integer tid; //商品类型

    private Integer aid; //地址

    private Integer status; //商品状态

    private Integer isCollect;// 用户是否收藏
}



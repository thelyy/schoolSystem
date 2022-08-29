package com.lyy.eu.service;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.pojo.TypeInfo;

import java.util.List;

public interface TypeService {

    List<TypeInfo> selectTname ();

    boolean addType(TypeInfo typeInfo);

    boolean updateType(TypeInfo typeInfo);

    List<GoodsInfo> findGoodByTidSend(Integer tid);

    boolean delType(TypeInfo typeInfo);

    List<TypeInfo> findTypeSend(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);
}

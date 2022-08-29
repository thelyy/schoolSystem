package com.lyy.eu.mapper;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.pojo.TypeInfo;

import java.util.List;

public interface TypeMapper {

    List<TypeInfo> selectTname ();

    List<GoodsInfo> findGoodByTidSend(Integer tid);

    boolean addType(TypeInfo typeInfo);

    boolean updateType(TypeInfo typeInfo);

    boolean delType(TypeInfo typeInfo);

    List<TypeInfo> findTypeSend(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);
}

package com.lyy.eu.service;

import com.lyy.eu.pojo.AddInfo;
import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface AddInfoService {

    List<AddInfo> selectAddInfo (Integer atid);

    List<AddInfo> findAddinfoEnd(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    boolean addAdd(AddInfo addInfo);

    boolean updateAdd(AddInfo addInfo);

    int delAdd(Integer aaid);

    AddInfo findStatus(AddInfo addInfo);

    List<AddInfo> findAlladd(AddInfo addInfo);

    boolean updateStatus(AddInfo addInfo);
}

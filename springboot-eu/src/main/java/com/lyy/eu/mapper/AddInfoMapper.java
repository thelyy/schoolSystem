package com.lyy.eu.mapper;

import com.lyy.eu.pojo.*;

import java.util.List;

public interface AddInfoMapper {

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

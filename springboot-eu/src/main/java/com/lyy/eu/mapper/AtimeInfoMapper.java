package com.lyy.eu.mapper;

import com.lyy.eu.pojo.AddInfo;
import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface AtimeInfoMapper {

    List<AtimeInfo> selectAtime();

    AtimeInfo selectAtid(AtimeInfo atimeInfo);

    List<AtimeInfo> findAtimeEnd(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    int addAtime(AtimeInfo atimeInfo);

    List<AtimeInfo> findAtime(Integer atid);

    boolean updateAtime(AtimeInfo atimeInfo);

    int delAtime(Integer atid);
}

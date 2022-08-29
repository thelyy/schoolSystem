package com.lyy.eu.service;

import com.lyy.eu.pojo.AtimeInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface AtimeInfoService {

    List<AtimeInfo> selectAtime();

    AtimeInfo selectAtid(AtimeInfo atimeInfo);

    List<AtimeInfo> findAtimeEnd(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    int addAtime(AtimeInfo atimeInfo);

//    boolean updateAtime(AtimeInfo atimeInfo);

    int delAtime(Integer atid);

    List<AtimeInfo> findAtime(Integer atid);
}

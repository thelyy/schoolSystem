package com.lyy.eu.service;

import com.lyy.eu.pojo.GoodsInfo;
import com.lyy.eu.pojo.Histroyinfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface HistoryInfoService {
    boolean addHistory(Histroyinfo histroyinfo);

    boolean delHistory(Histroyinfo histroyinfo);

    Histroyinfo findHistoryStatus(Histroyinfo histroyinfo);

    List<GoodsInfo> findHistory(PageInfo pageInfo);

    Integer findHistoryTotal(PageInfo pageInfo);
}

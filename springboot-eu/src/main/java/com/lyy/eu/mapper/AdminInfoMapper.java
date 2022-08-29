package com.lyy.eu.mapper;

import com.lyy.eu.pojo.AdminInfo;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface AdminInfoMapper {
    List<AdminInfo> findAdminSend(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    boolean updateAdmin(AdminInfo adminInfo);

    boolean delAdmin(AdminInfo adminInfo);

    AdminInfo loginAdminInfo(String tel, String pwd);

    boolean updatePwd(AdminInfo adminInfo);

    AdminInfo selectAdmin(Integer aid);

}

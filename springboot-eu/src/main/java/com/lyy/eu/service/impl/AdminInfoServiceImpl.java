package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.AdminInfoMapper;
import com.lyy.eu.pojo.AdminInfo;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired(required = false)
    private AdminInfoMapper adminInfoMapper;

    @Override
    public List<AdminInfo> findAdminSend(PageInfo pageInfo) {
        return adminInfoMapper.findAdminSend(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return adminInfoMapper.findTotal(pageInfo);
    }

    @Override
    public boolean updateAdmin(AdminInfo adminInfo) {
        return adminInfoMapper.updateAdmin(adminInfo);
    }

    @Override
    public boolean delAdmin(AdminInfo adminInfo) {
        return adminInfoMapper.delAdmin(adminInfo);
    }

    @Override
    public AdminInfo loginAdminInfo(String tel, String pwd) {
        return adminInfoMapper.loginAdminInfo(tel, pwd);
    }

    @Override
    public boolean updatePwd(AdminInfo adminInfo) {
        return adminInfoMapper.updatePwd(adminInfo);
    }

    @Override
    public AdminInfo selectAdmin(Integer aid) {
        return adminInfoMapper.selectAdmin(aid);
    }
}

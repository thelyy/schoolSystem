package com.lyy.eu.service.impl;

import com.lyy.eu.mapper.MemberMapper;
import com.lyy.eu.pojo.Member;
import com.lyy.eu.pojo.PageInfo;
import com.lyy.eu.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper euMapper;

    @Override
    public Member loginMember(String email, String pwd) {
        return euMapper.loginMember(email, pwd);
    }

    @Override
    public int regMember(Member member) {
        return euMapper.regMember(member);
    }

    @Override
    public Member findemail(String email) {
        return euMapper.findemail(email);
    }

    @Override
    public Member findUserByMember(Integer mid) {
        return euMapper.findUserByMember(mid);
    }

    @Override
    public List<Member> findMemberSend(PageInfo pageInfo) {
        return euMapper.findMemberSend(pageInfo);
    }

    @Override
    public Integer findTotal(PageInfo pageInfo) {
        return euMapper.findTotal(pageInfo);
    }

    @Override
    public boolean updateMember(Member member) {
        return euMapper.updateMember(member);
    }

    @Override
    public boolean delMember(Member member) {
        return euMapper.delMember(member);
    }

    @Override
    public boolean updateMemberInfo(Member member) {
        return euMapper.updateMemberInfo(member);
    }

    @Override
    public boolean updateHeadImg(Member member) {
        return euMapper.updateHeadImg(member);
    }

    @Override
    public int updatepwd(Member member) {
        return euMapper.updatepwd(member);
    }

}

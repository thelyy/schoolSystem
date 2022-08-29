package com.lyy.eu.mapper;

import com.lyy.eu.pojo.Member;
import com.lyy.eu.pojo.PageInfo;

import java.util.List;

public interface MemberMapper {
    Member loginMember(String email,String pwd);

    int regMember(Member member);

    Member findemail(String email);

    Member findUserByMember(Integer mid);

    List<Member> findMemberSend(PageInfo pageInfo);

    Integer findTotal(PageInfo pageInfo);

    boolean updateMember(Member member);

    boolean delMember(Member member);

    boolean updateMemberInfo(Member member);

    boolean updateHeadImg(Member member);

    int updatepwd(Member member);
}
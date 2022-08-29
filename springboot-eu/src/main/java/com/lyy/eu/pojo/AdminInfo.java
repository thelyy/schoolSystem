package com.lyy.eu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfo {
    private Integer aid;
    private String nickName;
    private String aname;
    private String pwd;
    private String tel;
    private int status; //1:在用 0:注销
}

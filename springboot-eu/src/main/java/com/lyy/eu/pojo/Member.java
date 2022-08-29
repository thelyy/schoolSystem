package com.lyy.eu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer mid;
    private String nickName;
    private String pwd;
    private String tel;
    private String email;
    private String photo;
    private int status; //1:在用 0:注销
}


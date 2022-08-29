package com.lyy.eu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Histroyinfo {
    Integer hid;

    private Integer mid;

    private Integer gid;

    private String date;
}

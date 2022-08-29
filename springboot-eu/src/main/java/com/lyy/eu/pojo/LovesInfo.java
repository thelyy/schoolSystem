package com.lyy.eu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LovesInfo {

    private Integer lid;

    private Integer mid;

    private Integer gid;

    private String gname;

    private String nickName;

    private Integer page;

    private Integer rows;

    public LovesInfo(Integer mid, Integer gid) {
        this.mid = mid;
        this.gid = gid;
    }
    public void LovesInfos(Integer mid, Integer gid, String gname, String nickName) {
        this.mid = mid;
        this.gid = gid;
        this.gname = gname;
        this.nickName = nickName;
    }
}

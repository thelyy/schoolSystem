package com.lyy.eu.pojo;

public class UsersBo {
    private String email;
    private String code;
    private String why;

    @Override
    public String toString() {
        return "UsersBo{" +
                "email='" + email + '\'' +
                '}';
    }

    public void setEmail(String email){this.email = email;}

    public String getEmail(){
        return email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}

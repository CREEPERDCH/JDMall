package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPERDCH on 2017/8/5.
 */

import org.litepal.crud.DataSupport;

public class AccountBean extends DataSupport {
    private String name;
    private String pwd;

    public AccountBean() {
    }

    public AccountBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/16.
 */

public class SMockPayParams {
    public String account;
    public String pwd;
    public String payPwd;
    public String tn;
    public long userId;

    public SMockPayParams(String account, String pwd, String payPwd, String tn, long userId) {
        this.account = account;
        this.pwd = pwd;
        this.payPwd = payPwd;
        this.tn = tn;
        this.userId = userId;
    }
}

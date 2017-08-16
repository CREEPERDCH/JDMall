package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/15.
 */

public class OrderProductBean {
    private double amount;
    private String piconUrl;
    private String pname;
    private String version;
    private Long pid;
    private int buyCount;

    public OrderProductBean() {
    }

    public OrderProductBean(double amount, String piconUrl, String pname, String version, Long pid, int buyCount) {
        this.amount = amount;
        this.piconUrl = piconUrl;
        this.pname = pname;
        this.version = version;
        this.pid = pid;
        this.buyCount = buyCount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPiconUrl() {
        return piconUrl;
    }

    public void setPiconUrl(String piconUrl) {
        this.piconUrl = piconUrl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "OrderProductBean{" +
                "amount=" + amount +
                ", piconUrl='" + piconUrl + '\'' +
                ", pname='" + pname + '\'' +
                ", version='" + version + '\'' +
                ", pid=" + pid +
                ", buyCount=" + buyCount +
                '}';
    }
}

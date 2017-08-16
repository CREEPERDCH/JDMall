package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/15.
 */

public class RAddOrder {
    private String orderNum;
    private double freight;
    private double totalPrice;
    private double allPrice;

    private long oid;
    private int errorType;//0-成功 1-产品没库存了 2-系统失败
    private int payWay;// 0在线支付 1货到付款
    private String buyTime;
    private String pname;
    private String tn;

    public RAddOrder() {
    }

    public RAddOrder(String orderNum, double freight, double totalPrice, double allPrice, long oid,
                     int errorType, int payWay, String buyTime, String pname, String tn) {
        this.orderNum = orderNum;
        this.freight = freight;
        this.totalPrice = totalPrice;
        this.allPrice = allPrice;
        this.oid = oid;
        this.errorType = errorType;
        this.payWay = payWay;
        this.buyTime = buyTime;
        this.pname = pname;
        this.tn = tn;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    @Override
    public String toString() {
        return "RAddOrder{" +
                "orderNum='" + orderNum + '\'' +
                ", freight=" + freight +
                ", totalPrice=" + totalPrice +
                ", allPrice=" + allPrice +
                ", oid=" + oid +
                ", errorType=" + errorType +
                ", payWay=" + payWay +
                ", buyTime='" + buyTime + '\'' +
                ", pname='" + pname + '\'' +
                ", tn='" + tn + '\'' +
                '}';
    }
}

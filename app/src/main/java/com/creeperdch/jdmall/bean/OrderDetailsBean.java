package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/15.
 */

public class OrderDetailsBean {
    private String oid;
    private int payWay;
    private String tn;

    private String orderNum;
    private int status;
    private String address;//地址JSON
    private String items;//JSON数组 代表订单里面的商品列表
    private double totalPrice;//总价
    private double freight;//运费
    private String buyTime;

    public OrderDetailsBean() {
    }

    public OrderDetailsBean(String oid, int payWay, String tn, String orderNum, int status, String address,
                            String items, double totalPrice, double freight, String buyTime) {
        this.oid = oid;
        this.payWay = payWay;
        this.tn = tn;
        this.orderNum = orderNum;
        this.status = status;
        this.address = address;
        this.items = items;
        this.totalPrice = totalPrice;
        this.freight = freight;
        this.buyTime = buyTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    @Override
    public String toString() {
        return "OrderDetailsBean{" +
                "oid='" + oid + '\'' +
                ", payWay=" + payWay +
                ", tn='" + tn + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", items='" + items + '\'' +
                ", totalPrice=" + totalPrice +
                ", freight=" + freight +
                ", buyTime='" + buyTime + '\'' +
                '}';
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by wwwwy on 2017/8/6.
 */

public class SecondKillBean {
    private long productId;
    private String iconUrl;
    private double pointPrice;
    private double allPrice;

    private String timeLeft;//秒杀剩余时间（分钟）
    private int type;//秒杀类型（1抢年货，2超值，3热卖）

    public SecondKillBean() {
    }

    public SecondKillBean(long productId, String iconUrl, double pointPrice, double allPrice, String timeLeft, int type) {
        this.productId = productId;
        this.iconUrl = iconUrl;
        this.pointPrice = pointPrice;
        this.allPrice = allPrice;
        this.timeLeft = timeLeft;
        this.type = type;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public double getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(double pointPrice) {
        this.pointPrice = pointPrice;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SecondKillBean{" + "productId=" + productId + ", iconUrl='" + iconUrl + '\'' + ", pointPrice=" + pointPrice + ", allPrice=" + allPrice + ", timeLeft='" + timeLeft + '\'' + ", type=" + type + '}';
    }
}

package com.creeperdch.jdmall.bean;

/*
 * Created by CREEPER_D on 2017/8/9.
 */

import java.io.Serializable;

public class ShopCarBean implements Serializable {
    private long id;//购物车明细id
    private long pid;//商品id
    private String pimageUrl;
    private String pname;
    private double pprice;
    private int buyCount;
    private String pversion;

    private long storeId;//商店id
    private String storeName;
    private int stockCount;

    public boolean isChecked;

    public ShopCarBean() {
    }

    public ShopCarBean(long id, long pid, String pimageUrl, String pname, double pprice, int buyCount, String pversion, long storeId,
                       String storeName, int stockCount) {
        this.id = id;
        this.pid = pid;
        this.pimageUrl = pimageUrl;
        this.pname = pname;
        this.pprice = pprice;
        this.buyCount = buyCount;
        this.pversion = pversion;
        this.storeId = storeId;
        this.storeName = storeName;
        this.stockCount = stockCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getPimageUrl() {
        return pimageUrl;
    }

    public void setPimageUrl(String pimageUrl) {
        this.pimageUrl = pimageUrl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getPversion() {
        return pversion;
    }

    public void setPversion(String pversion) {
        this.pversion = pversion;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public String toString() {
        return "ShopCarBean{" +
                "id=" + id +
                ", pid=" + pid +
                ", pimageUrl='" + pimageUrl + '\'' +
                ", pname='" + pname + '\'' +
                ", pprice=" + pprice +
                ", buyCount=" + buyCount +
                ", pversion='" + pversion + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", stockCount=" + stockCount +
                '}';
    }
}

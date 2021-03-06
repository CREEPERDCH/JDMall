package com.creeperdch.jdmall.adapter;
/*
 * Created by wwwwy on 2017/8/7.
 */

public class RProductList {
    private long id;
    private double price;
    private String name;
    private String iconUrl;
    private int commentCount;
    private int favcomRate;

    public RProductList() {
    }

    public RProductList(long id, double price, String name, String iconUrl, int commentCount, int favcomRate) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.iconUrl = iconUrl;
        this.commentCount = commentCount;
        this.favcomRate = favcomRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavcomRate() {
        return favcomRate;
    }

    public void setFavcomRate(int favcomRate) {
        this.favcomRate = favcomRate;
    }

    @Override
    public String toString() {
        return "RProductList{" + "id=" + id + ", price=" + price + ", name='" + name + '\'' + ", iconUrl='" + iconUrl + '\'' + ", commentCount=" + commentCount + ", favcomRate=" + favcomRate + '}';
    }
}

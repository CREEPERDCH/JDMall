package com.creeperdch.jdmall.bean;
/*
 * Created by wwwwy on 2017/8/6.
 */

public class RecommandProductBean {
    private long productId;
    private double price;
    private String name;
    private String iconUrl;

    public RecommandProductBean() {
    }

    public RecommandProductBean(long productId, double price, String name, String iconUrl) {
        this.productId = productId;
        this.price = price;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "RecommandProductBean{" + "productId=" + productId + ", price=" + price + ", name='" + name + '\'' + ", iconUrl='" + iconUrl + '\'' + '}';
    }
}

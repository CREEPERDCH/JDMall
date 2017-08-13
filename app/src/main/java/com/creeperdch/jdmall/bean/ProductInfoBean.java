package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class ProductInfoBean {
    private String id;
    private String imgUrls;//存储JSON格式的一系列的图片地址
    private String name;//商品名称
    private String recomProduct;// 推荐商品名称
    private String recomProductId;// 推荐商品ID

    private boolean ifSaleOneself;// 是否自营[JD自营 冠名===>天猫]
    private double price;
    private long timeLeft;// 如果是秒杀 则返回时间 分钟
    private String typeList;// 商品型号 JSON数组
    private int stockCount;// 当前库存
    private int positiveRate;// 好评率
    private int commentCount;// 评论人数

    public ProductInfoBean() {
    }

    public ProductInfoBean(String id, String imgUrls, String name, String recomProduct, String recomProductId, boolean ifSaleOneself,
                           double price, long timeLeft, String typeList, int stockCount, int positiveRate, int commentCount) {
        this.id = id;
        this.imgUrls = imgUrls;
        this.name = name;
        this.recomProduct = recomProduct;
        this.recomProductId = recomProductId;
        this.ifSaleOneself = ifSaleOneself;
        this.price = price;
        this.timeLeft = timeLeft;
        this.typeList = typeList;
        this.stockCount = stockCount;
        this.positiveRate = positiveRate;
        this.commentCount = commentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecomProduct() {
        return recomProduct;
    }

    public void setRecomProduct(String recomProduct) {
        this.recomProduct = recomProduct;
    }

    public String getRecomProductId() {
        return recomProductId;
    }

    public void setRecomProductId(String recomProductId) {
        this.recomProductId = recomProductId;
    }

    public boolean isIfSaleOneself() {
        return ifSaleOneself;
    }

    public void setIfSaleOneself(boolean ifSaleOneself) {
        this.ifSaleOneself = ifSaleOneself;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(int positiveRate) {
        this.positiveRate = positiveRate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "ProductInfoBean{" +
                "id='" + id + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", name='" + name + '\'' +
                ", recomProduct='" + recomProduct + '\'' +
                ", recomProductId='" + recomProductId + '\'' +
                ", ifSaleOneself=" + ifSaleOneself +
                ", price=" + price +
                ", timeLeft=" + timeLeft +
                ", typeList='" + typeList + '\'' +
                ", stockCount=" + stockCount +
                ", positiveRate=" + positiveRate +
                ", commentCount=" + commentCount +
                '}';
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class CommentDetailsbean {
    private long id;
    private int userLevel;

    private String userImg;
    private String userName;
    private String commentTime;//评论时间

    private int rate;
    private String comment;
    private String imgUrls;//评论图片路径JSON数组
    private String buyTime;
    private String productType;//产品版本类型

    private int loveCount;
    private int subComment;

    public CommentDetailsbean() {
    }

    public CommentDetailsbean(long id, int userLevel, String userImg, String userName, String commentTime, int rate, String comment,
                              String imgUrls, String buyTime, String productType, int loveCount, int subComment) {
        this.id = id;
        this.userLevel = userLevel;
        this.userImg = userImg;
        this.userName = userName;
        this.commentTime = commentTime;
        this.rate = rate;
        this.comment = comment;
        this.imgUrls = imgUrls;
        this.buyTime = buyTime;
        this.productType = productType;
        this.loveCount = loveCount;
        this.subComment = subComment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public int getSubComment() {
        return subComment;
    }

    public void setSubComment(int subComment) {
        this.subComment = subComment;
    }

    @Override
    public String toString() {
        return "CommentDetailsbean{" +
                "id=" + id +
                ", userLevel=" + userLevel +
                ", userImg='" + userImg + '\'' +
                ", userName='" + userName + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", productType='" + productType + '\'' +
                ", loveCount=" + loveCount +
                ", subComment=" + subComment +
                '}';
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class GoodCommentBean {
    private int rate;//星星数 最多5个
    private String userName;
    private String comment;
    private String imgUrls;//评论图片路径  JSON数组

    private String time;
    private int type;

    public GoodCommentBean() {
    }

    public GoodCommentBean(int rate, String userName, String comment, String imgUrls, String time, int type) {
        this.rate = rate;
        this.userName = userName;
        this.comment = comment;
        this.imgUrls = imgUrls;
        this.time = time;
        this.type = type;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GoodCommentBean{" +
                "rate=" + rate +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                '}';
    }
}

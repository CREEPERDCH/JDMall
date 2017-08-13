package com.creeperdch.jdmall.bean;
/*
 * Created by wwwwy on 2017/8/7.
 */

public class TopCategoryBean {
    private long id;
    private String bannerUrl;
    private String name;

    public TopCategoryBean() {
    }

    public TopCategoryBean(long id, String bannerUrl, String name) {
        this.id = id;
        this.bannerUrl = bannerUrl;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TopCategoryBean{" + "id=" + id + ", bannerUrl='" + bannerUrl + '\'' + ", name='" + name + '\'' + '}';
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by wwwwy on 2017/8/7.
 */

public class SubCateGoryBean {
    private long id;
    private String name;
    private String thirdCategory;//三级分类的JSON数组

    public SubCateGoryBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        this.thirdCategory = thirdCategory;
    }

    public SubCateGoryBean(long id, String name, String thirdCategory) {

        this.id = id;
        this.name = name;
        this.thirdCategory = thirdCategory;
    }

    @Override
    public String toString() {
        return "SubCateGoryBean{" + "id=" + id + ", name='" + name + '\'' + ", thirdCategory='" + thirdCategory + '\'' + '}';
    }
}

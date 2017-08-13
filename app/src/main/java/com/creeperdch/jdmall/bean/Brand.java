package com.creeperdch.jdmall.bean;
/*
 * Created by wwwwy on 2017/8/7.
 */

public class Brand {
    private long id;
    private String name;

    public Brand() {
    }

    public Brand(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Brand{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

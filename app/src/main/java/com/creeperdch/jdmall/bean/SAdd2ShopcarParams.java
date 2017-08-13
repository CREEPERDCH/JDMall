package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class SAdd2ShopcarParams {
    public long userId;
    public long productId;
    public int buyCount;
    public String pVersion;

    public SAdd2ShopcarParams(long userId, long productId, int buyCount, String pVersion) {
        this.userId = userId;
        this.productId = productId;
        this.buyCount = buyCount;
        this.pVersion = pVersion;
    }
}

package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import java.io.Serializable;

public class ReceiverAddress implements Serializable {
    private long id;
    private boolean isDefault;//默认地址
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;

    public ReceiverAddress() {
    }

    public ReceiverAddress(long id, boolean isDefault, String receiverName, String receiverPhone, String receiverAddress) {
        this.id = id;
        this.isDefault = isDefault;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @Override
    public String toString() {
        return "ReceiverAddress{" +
                "id=" + id +
                ", isDefault=" + isDefault +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                '}';
    }
}

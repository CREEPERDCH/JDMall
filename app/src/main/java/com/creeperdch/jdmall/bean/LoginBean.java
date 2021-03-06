package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPERDCH on 2017/8/4.
 */

public class LoginBean {
    private long id;
    private String userName;
    private String userIcon;
    private int waitPayCount;//待付款数
    private int waitReceiveCount;//待收货数
    private int userLevel;//用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员

    public String getUserLevelStr() {
        switch (userLevel) {
            case 1:
                return "注册会员";
            case 2:
                return "铜牌会员";
            case 3:
                return "银牌会员";
            case 4:
                return "金牌会员";
            case 5:
                return "钻石会员";
        }
        return "";
    }

    public LoginBean() {
    }

    public LoginBean(long id, String userName, String userIcon, int waitPayCount, int waitReceiveCount, int userLevel) {
        this.id = id;
        this.userName = userName;
        this.userIcon = userIcon;
        this.waitPayCount = waitPayCount;
        this.waitReceiveCount = waitReceiveCount;
        this.userLevel = userLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "LoginBean{" + "id=" + id + ", userName='" + userName + '\'' + ", userIcon='" + userIcon + '\'' + ", waitPayCount=" + waitPayCount + ", waitReceiveCount=" + waitReceiveCount + ", userLevel=" + userLevel + '}';
    }
}

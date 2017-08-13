package com.creeperdch.jdmall;

import android.app.Application;

import com.creeperdch.jdmall.bean.LoginBean;

import org.litepal.LitePal;

/*
 * Created by CREEPERDCH on 2017/8/5.
 */

public class JDMallApplication extends Application {

    public static LoginBean mUserInfo;

    /**
     * @return -1 代表拿不到当前用户的登录ID
     */
    public static long getUserId() {
        return mUserInfo != null ? mUserInfo.getId() : -1;
    }

    //应用启动时调用
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        LitePal.getDatabase();
    }
}

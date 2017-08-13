package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPERDCH on 2017/8/4.
 */


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.AccountBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.AESUtil;
import com.creeperdch.jdmall.utils.HttpUtil;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

public class UserController extends BaseController {

    public UserController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.LOGIN_ACTION:
                onModelChanged(action, login((String) values[0], (String) values[1]));
                break;
            case IDiyMessage.REGIST_ACTION:
                onModelChanged(action, regist((String) values[0], (String) values[1]));
                break;
            case IDiyMessage.SAVE_ACCOUNT_ACTION:
                saveUserAccount((String) values[0], (String) values[1]);
                break;
            case IDiyMessage.QUERY_ACCOUNT_ACTION:
                onModelChanged(action, queryUser());
                break;
        }
    }

    private void clearAccountTable() {
        DataSupport.deleteAll(AccountBean.class);
    }

    private AccountBean queryUser() {
        List<AccountBean> accounts = DataSupport.findAll(AccountBean.class);
        if (accounts.size() > 0) {
            AccountBean account = accounts.get(0);
            if (!TextUtils.isEmpty(account.getPwd())) {
                try {
                    account.setPwd(AESUtil.decrypt(account.getPwd()));
                    return account;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveUserAccount(String name, String pwd) {
        DataSupport.deleteAll(AccountBean.class);
        try {
            pwd = AESUtil.encrypt(pwd);
            AccountBean accountBean = new AccountBean(name, pwd);
            accountBean.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AccountBean accountBean = new AccountBean(name, "");
        accountBean.save();
    }

    private ResultBean login(String name, String pwd) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("username", name);
        paramsMap.put("pwd", pwd);
        String jsonStr = HttpUtil.doPost(HttpConstant.LOGIN_URL, paramsMap);
        Log.d("dxq", "login: " + jsonStr);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }

    private ResultBean regist(String name, String pwd) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("username", name);
        paramsMap.put("pwd", pwd);
        String jsonStr = HttpUtil.doPost(HttpConstant.REGIST_URL, paramsMap);
        Log.d("dxq", "regist: " + jsonStr);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}

package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/16.
 */


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.PayInfoBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SMockPayParams;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.HashMap;

public class AlipayController extends BaseController {

    public AlipayController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.GET_PAYINFO_ACTION:
                onModelChanged(action, getPayInfo((String) values[0], (Long) values[1]));
                break;
            case IDiyMessage.MOCK_PAY_ACTION:
                onModelChanged(action, mockPay((SMockPayParams) values[0]));
                break;
        }
    }

    private PayInfoBean getPayInfo(String tn, long userId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("tn", tn);
        paramsMap.put("userId", userId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.GET_PAYINFO_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), PayInfoBean.class);
        }
        return null;
    }

    private ResultBean mockPay(SMockPayParams paramsBean) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("account", paramsBean.account);
        paramsMap.put("apwd", paramsBean.pwd);
        paramsMap.put("ppwd", paramsBean.payPwd);
        paramsMap.put("tn", paramsBean.tn);
        paramsMap.put("userId", paramsBean.userId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.MOCK_PAY_URL, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}

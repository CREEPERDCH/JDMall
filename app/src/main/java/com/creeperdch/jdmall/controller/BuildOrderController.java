package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

public class BuildOrderController extends BaseController {
    public BuildOrderController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.DEFAULT_RECEIVER_ACTION:
                onModelChanged(action, defaultReceiver((Long) values[0]));
                break;
        }
    }

    private ReceiverAddress defaultReceiver(long userId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("isDefault", "true");
        String jsonStr = HttpUtil.doPost(HttpConstant.ADDRESS_LIST_ACTION, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            List<ReceiverAddress> datas = JSON.parseArray(resultBean.getResult(), ReceiverAddress.class);
            if (datas.size() > 0) {
                return datas.get(0);
            }
        }
        return null;
    }
}

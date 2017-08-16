package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.RAddOrder;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SBuildOrderParams;
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
            case IDiyMessage.ADD_ORDER_ACTION:
                onModelChanged(action, buildOrder((SBuildOrderParams) values[0]));
                break;
            case IDiyMessage.CANCEL_ORDER:
                onModelChanged(action, cancelOrder((Long) values[0], (Long) values[1]));
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

    private RAddOrder buildOrder(SBuildOrderParams buildOrderParams) {
        HashMap<String, String> paramsMap = new HashMap<>();
        String paramsJson = JSON.toJSONString(buildOrderParams);
        paramsMap.put("detail", paramsJson);
        String jsonStr = HttpUtil.doPost(HttpConstant.ADD_ORDER_URL, paramsMap);
        ResultBean result = JSON.parseObject(jsonStr, ResultBean.class);
        if (result.isSuccess()) {
            return JSON.parseObject(result.getResult(), RAddOrder.class);
        }
        return null;
    }

    private ResultBean cancelOrder(long userId, long oid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("oid", oid + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.CANCEL_ORDER_URL, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}

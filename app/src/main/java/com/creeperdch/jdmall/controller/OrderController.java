package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.OrderDetailsBean;
import com.creeperdch.jdmall.bean.OrderListBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.consf.OrderStatus;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderController extends BaseController {
    public OrderController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.CANCEL_ORDER:
                onModelChanged(action, cancelOrder((Long) values[0], (Long) values[1]));
                break;
            case IDiyMessage.ORDER_LIST_ACTION:
                onModelChanged(action, loadOrderList((Long) values[0], (Integer) values[1]));
                break;
            case IDiyMessage.ORDER_DETAILS_ACTION:
                onModelChanged(action, loadOrderDetails((Long) values[0], (Long) values[1]));
                break;
            case IDiyMessage.CONFIRM_RECEIVER_ORDER_ACTION:
                onModelChanged(action, confirmReceiveOrder((Long) values[0], (Long) values[1]));
                break;
        }
    }

    private ResultBean cancelOrder(long userId, long oid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("oid", oid + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.CANCEL_ORDER_URL, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }

    private List<OrderListBean> loadOrderList(long userId, int orderStatus) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        if (orderStatus != OrderStatus.ALL_ORDER) {
            paramsMap.put("status", orderStatus + "");
        }
        String jsonStr = HttpUtil.doPost(HttpConstant.ORDER_LIST_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), OrderListBean.class);
        }
        return new ArrayList<>();
    }

    private OrderDetailsBean loadOrderDetails(long userId, long oid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("id", oid + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.ORDER_DETAILS_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), OrderDetailsBean.class);
        }
        return null;
    }

    private ResultBean confirmReceiveOrder(long userId, long oid) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("oid", oid + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.CONFIRM_ORDER_URL, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}

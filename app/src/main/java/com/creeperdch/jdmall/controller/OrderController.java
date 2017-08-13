package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
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
            case IDiyMessage.ORDER_LIST_ACTION:
                onModelChanged(action, loadOrderList((Long) values[0], (Integer) values[1]));
                break;
        }
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
}

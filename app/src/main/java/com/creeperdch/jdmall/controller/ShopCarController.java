package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.ShopCarBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopCarController extends BaseController {
    public ShopCarController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.SHOPCAR_LIST_ACTION:
                onModelChanged(action, loadShopCar((Long) values[0]));
                break;
            case IDiyMessage.DEL_SHOPCAR_ITEM:
                ResultBean ifDelShopCar = delShopCar((Long) values[0], (Long) values[1]);
                onModelChanged(action, ifDelShopCar);
                break;
        }
    }

    private List<ShopCarBean> loadShopCar(long userId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.SHOPCAR_LIST_ACTION, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), ShopCarBean.class);
        }
        return new ArrayList<>();
    }

    private ResultBean delShopCar(long userId, long shopcarId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        paramsMap.put("id", shopcarId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.DEL_SHOPCAR_ACTION, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }
}

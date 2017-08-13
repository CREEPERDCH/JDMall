package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SAddAddressParams;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressController extends BaseController {

    public AddressController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.ADDRESS_LIST_ACTION:
                onModelChanged(action, loadAddressList((Long) values[0]));
                break;
            case IDiyMessage.DEL_ADDRESS_ACTION:
                onModelChanged(action, delAddress((Long) values[0], (Long) values[1]));
                break;
            case IDiyMessage.PROVINCE_ACTION:
                onModelChanged(action, loadProvince());
                break;
            case IDiyMessage.CITY_ACTION:
                onModelChanged(action, loadCity((String) values[0]));
                break;
            case IDiyMessage.AREA_ACTION:
                onModelChanged(action, loadArea((String) values[0]));
                break;
            case IDiyMessage.ADD_ADDRESS_ACTION:
                onModelChanged(action, addAddress((SAddAddressParams) values[0]));
                break;
        }
    }

    private List<Area> loadProvince() {
        String jsonStr = HttpUtil.doGet(HttpConstant.PROVINCE_URL);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Area.class);
        }
        return new ArrayList<>();
    }

    private List<Area> loadCity(String provinceCode) {
        String jsonStr = HttpUtil.doGet(HttpConstant.CITY_URL + "?fcode=" + provinceCode);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Area.class);
        }
        return new ArrayList<>();
    }

    private List<Area> loadArea(String cityCode) {
        String jsonStr = HttpUtil.doGet(HttpConstant.AREA_URL + "?fcode=" + cityCode);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Area.class);
        }
        return new ArrayList<>();
    }

    private ResultBean addAddress(SAddAddressParams paramsBean) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", paramsBean.userId + "");
        paramsMap.put("name", paramsBean.name);
        paramsMap.put("phone", paramsBean.phone);
        paramsMap.put("provinceCode", paramsBean.provinceCode);
        paramsMap.put("cityCode", paramsBean.cityCode);
        paramsMap.put("distCode", paramsBean.distCode);
        paramsMap.put("addressDetails", paramsBean.addressDetails);
        paramsMap.put("isDefault", paramsBean.isDefault + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.ADD_ADDRESS_URL, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }

    private ResultBean delAddress(long userId, long addressId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("id", addressId + "");
        paramsMap.put("userId", userId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.DEL_ADDRESS_ACTION, paramsMap);
        return JSON.parseObject(jsonStr, ResultBean.class);
    }

    private List<ReceiverAddress> loadAddressList(long userId) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId + "");
        String jsonStr = HttpUtil.doPost(HttpConstant.ADDRESS_LIST_ACTION, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), ReceiverAddress.class);
        }
        return new ArrayList<>();
    }

    public static class Area {

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}

package com.creeperdch.jdmall.controller;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.adapter.RProductList;
import com.creeperdch.jdmall.bean.Brand;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SProductListParams;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductListController extends BaseController {
    public ProductListController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.BRAND_ACTION:
                onModelChanged(action, loadBrand((Long) values[0]));
                break;
            case IDiyMessage.PRODUCT_LIST_ACTION:
                onModelChanged(action, loadProductList((SProductListParams) values[0]));
                break;
        }
    }

    private List<Brand> loadBrand(long topCategoryId) {
        String jsonStr = HttpUtil.doGet(HttpConstant.BRAND_URL + "?categoryId=" + topCategoryId);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Brand.class);
        }
        return new ArrayList<>();
    }

    private List<RProductList> loadProductList(SProductListParams paramsBean) {
        HashMap<String, String> paramsMap = builtProductListParam(paramsBean);
        String jsonStr = HttpUtil.doPost(HttpConstant.PRODUCT_LIST_URL, paramsMap);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String rowsJson = jsonObject.getString("rows");
                return JSON.parseArray(rowsJson, RProductList.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private HashMap<String, String> builtProductListParam(SProductListParams paramsBean) {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("categoryId", paramsBean.categoryId + "");
        paramsMap.put("filterType", paramsBean.filterType + "");
        if (paramsBean.sortType != SProductListParams.SORT_NONE) {
            paramsMap.put("sortType", paramsBean.sortType + "");
        }
        paramsMap.put("deliverChoose", paramsBean.deliverChoose + "");
        if (paramsBean.brandId != 0) {
            paramsMap.put("brandId", paramsBean.brandId + "");
        }
        return paramsMap;
    }
}

package com.creeperdch.jdmall.controller;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SubCateGoryBean;
import com.creeperdch.jdmall.bean.TopCategoryBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class CateGoryController extends BaseController {
    public CateGoryController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.TOP_CATEGORY_ACTION:
                onModelChanged(action, loadTopCategory());
                break;
            case IDiyMessage.SUB_CATEGORY_ACTION:
                onModelChanged(action, loadSubCategory((long) values[0]));
                break;
        }
    }

    private List<TopCategoryBean> loadTopCategory() {
        String jsonStr = HttpUtil.doGet(HttpConstant.CATEGORY_URL);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), TopCategoryBean.class);
        }
        return new ArrayList<>();
    }

    private Object loadSubCategory(long topCategoryId) {
        String jsonStr = HttpUtil.doGet(HttpConstant.CATEGORY_URL + "?parentId=" + topCategoryId);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), SubCateGoryBean.class);
        }
        return new ArrayList<>();
    }
}

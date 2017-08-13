package com.creeperdch.jdmall.controller;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.BannerBean;
import com.creeperdch.jdmall.bean.RecommandProductBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SecondKillBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends BaseController {
    public HomeController(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.BANNER_ACTION:
                onModelChanged(action, loadBanner((Integer) values[0]));
                break;
            case IDiyMessage.SECOND_KILL_ACTION:
                onModelChanged(action, loadSecondKill());
                break;
            case IDiyMessage.RECOMMAND_PRODUCT_ACTION:
                onModelChanged(action, loadRecommandProduct());
                break;
        }
    }

    private List<RecommandProductBean> loadRecommandProduct() {
        List<RecommandProductBean> datas = new ArrayList<>();
        String jsonStr = HttpUtil.doGet(HttpConstant.RECOMMAND_PRODUCT_URL);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String rowsJson = jsonObject.getString("rows");
                datas = JSON.parseArray(rowsJson, RecommandProductBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }

    private List<SecondKillBean> loadSecondKill() {
        List<SecondKillBean> datas = new ArrayList<>();
        String jsonStr = HttpUtil.doGet(HttpConstant.SECKILL_URL);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String rowsJson = jsonObject.getString("rows");
                datas = JSON.parseArray(rowsJson, SecondKillBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }

    private List<BannerBean> loadBanner(int type) {
        List<BannerBean> datas = new ArrayList<>();
        if (type != 1 && type != 2) {
            return datas;
        }
        String jsonStr = HttpUtil.doGet(HttpConstant.BANNER_URL + "?adKind=" + type);
        ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
        if (resultBean.isSuccess()) {
            datas = JSON.parseArray(resultBean.getResult(), BannerBean.class);
        }
        return datas;
    }
}

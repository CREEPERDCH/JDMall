package com.creeperdch.jdmall.mvp.model;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.utils.HttpUtil;

import java.util.HashMap;

public class ResetPwdModellmp implements IResetPwdModel {

    private IPwdResetListener mListener;

    public ResetPwdModellmp(IPwdResetListener listener) {
        this.mListener = listener;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mListener != null) {
                mListener.onPwdReseted((ResultBean) msg.obj);
            }
        }
    };

    @Override
    public void resetPwd(final String name) {
        new Thread() {
            public void run() {
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("username", name);
                String jsonStr = HttpUtil.doPost(HttpConstant.RESET_URL, paramsMap);
                ResultBean resultBean = JSON.parseObject(jsonStr, ResultBean.class);
                mHandler.obtainMessage(0, resultBean).sendToTarget();
            }
        }.start();
    }
}

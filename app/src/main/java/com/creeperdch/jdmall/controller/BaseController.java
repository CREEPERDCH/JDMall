package com.creeperdch.jdmall.controller;
/*
 * Created by CREEPERDCH on 2017/8/4.
 */

import android.content.Context;

import com.creeperdch.jdmall.listener.IModelChangedListener;

public abstract class BaseController {

    protected IModelChangedListener mListener;
    protected Context mContext;

    public BaseController(Context context) {
        mContext = context;
    }

    public void setListener(IModelChangedListener listener) {
        mListener = listener;
    }

    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            public void run() {
                //调用子类做具体的业务
                handleMessage(action, values);
            }
        }.start();
    }

    public abstract void handleMessage(int action, Object... values);

    protected void onModelChanged(int action, Object result) {
        if (mListener != null) {
            mListener.onModelChanged(action, result);
        }
    }
}

package com.creeperdch.jdmall.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.creeperdch.jdmall.controller.BaseController;
import com.creeperdch.jdmall.listener.IModelChangedListener;

import java.lang.ref.WeakReference;

/*
 * Created by CREEPERDCH on 2017/8/4.
 */

public class BaseActivity extends AppCompatActivity implements IModelChangedListener {

    protected BaseController mController;

    public static class BaseHandler extends Handler {

        WeakReference<BaseActivity> reference;

        public BaseHandler(BaseActivity activity) {
            reference = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference != null && reference.get() != null) {
                reference.get().handleUI(msg);
            }
        }
    }

    protected BaseHandler mHandler = new BaseHandler(this);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    protected void handleUI(Message msg) {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initController() {

    }

    @Override
    public void onModelChanged(int action, Object returnBean) {
        mHandler.obtainMessage(action, returnBean).sendToTarget();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void log(String msg) {
        Log.d("dxq", msg);
    }

    public void goBack(View v) {
        finish();
    }

    public void add2ShopCar(View v) {

    }
}

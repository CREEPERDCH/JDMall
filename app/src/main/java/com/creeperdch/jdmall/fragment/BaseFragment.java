package com.creeperdch.jdmall.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.creeperdch.jdmall.controller.BaseController;
import com.creeperdch.jdmall.listener.IModelChangedListener;

/*
 * Created by wwwwy on 2017/8/6.
 */

public class BaseFragment extends Fragment implements IModelChangedListener {

    protected BaseController mController;

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handleUI(msg);
        }
    };

    protected void handleUI(Message msg) {

    }

    protected void initController() {

    }

    @Override
    public void onModelChanged(int action, Object returnBean) {
        mHandler.obtainMessage(action, returnBean).sendToTarget();
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void log(String msg) {
        Log.d("dxq", msg);
    }
}

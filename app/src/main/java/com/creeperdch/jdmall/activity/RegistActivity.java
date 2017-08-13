package com.creeperdch.jdmall.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.UserController;
import com.creeperdch.jdmall.utils.ActivityUtil;

public class RegistActivity extends BaseActivity {

    private EditText mUsernameEt;
    private EditText mPwdEt;
    private EditText mSurepwdEt;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.REGIST_ACTION) {
            handleRegistResult((ResultBean) msg.obj);
        }
    }

    private void handleRegistResult(ResultBean resultBean) {
        if (resultBean.isSuccess()) {
            showToast("注册成功了!");
            ActivityUtil.startNew(this, LoginActivity.class, true);
        } else {
            String errorMsg = resultBean.getErrorMsg();
            showToast(resultBean.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        initController();
    }

    @Override
    protected void initView() {
        mUsernameEt = (EditText) findViewById(R.id.username_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
        mSurepwdEt = (EditText) findViewById(R.id.surepwd_et);
    }

    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setListener(this);
    }

    public void registClick(View view) {
        String name = mUsernameEt.getText().toString().trim();
        String pwd = mPwdEt.getText().toString().trim();
        String surePwd = mSurepwdEt.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(surePwd)) {
            showToast("请输入完整的信息!");
            return;
        }
        if (!surePwd.equals(pwd)) {
            showToast("两次输入不一致,请重试!");
            return;
        }
        mController.sendAsyncMessage(IDiyMessage.REGIST_ACTION, name, pwd);
    }
}

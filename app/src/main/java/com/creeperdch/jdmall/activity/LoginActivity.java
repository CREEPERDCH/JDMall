package com.creeperdch.jdmall.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.AccountBean;
import com.creeperdch.jdmall.bean.LoginBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.UserController;
import com.creeperdch.jdmall.mvp.view.ResetActivity;
import com.creeperdch.jdmall.utils.ActivityUtil;

public class LoginActivity extends BaseActivity {

    private EditText mNameEt;
    private EditText mPwdEt;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.LOGIN_ACTION) {
            ResultBean resultBean = (ResultBean) msg.obj;
            if (!resultBean.isSuccess()) {
                showToast(resultBean.getErrorMsg());
                return;
            }
            showToast("登录成功");

            //save account info to database
            String name = mNameEt.getText().toString().trim();
            String pwd = mPwdEt.getText().toString().trim();
            mController.sendAsyncMessage(IDiyMessage.SAVE_ACCOUNT_ACTION, name, pwd);

            //put the message after login success to memory
            JDMallApplication app = (JDMallApplication) getApplication();
            app.mUserInfo = JSON.parseObject(resultBean.getResult(), LoginBean.class);

            //to HomePage
            ActivityUtil.startNew(this, MainActivity.class, true);
        } else if (msg.what == IDiyMessage.QUERY_ACCOUNT_ACTION) {
            if (msg.obj != null) {
                AccountBean account = (AccountBean) msg.obj;
                mNameEt.setText(account.getName());
                mPwdEt.setText(account.getPwd());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initController();
        readAccount();
    }

    @Override
    protected void initView() {
        mNameEt = (EditText) findViewById(R.id.name_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
    }

    public void initController() {
        mController = new UserController(this);
        //如果数据返回了,就通过listener通知
        mController.setListener(this);
    }

    public void readAccount() {
        mController.sendAsyncMessage(IDiyMessage.QUERY_ACCOUNT_ACTION);
    }

    public void loginClick(View view) {
        String name = mNameEt.getText().toString().trim();
        String pwd = mPwdEt.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("用户名不能为Null!");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码!");
            return;
        }
        // TODO: 实现网络请求
        mController.sendAsyncMessage(IDiyMessage.LOGIN_ACTION, name, pwd);
    }

    public void registClick(View view) {
        ActivityUtil.startNew(this, RegistActivity.class, false);
    }

    public void resetPwdClick(View view) {
        ActivityUtil.startNew(this, ResetActivity.class, false);
    }
}

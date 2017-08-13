package com.creeperdch.jdmall.mvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.LoginActivity;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.mvp.presenter.IResetPwdPresenter;
import com.creeperdch.jdmall.mvp.presenter.ResetPwdPresenterImpl;
import com.creeperdch.jdmall.utils.ActivityUtil;

public class ResetActivity extends AppCompatActivity implements IResetView {

    private EditText mUsernameEt;
    private IResetPwdPresenter mPresenter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        initView();
        mPresenter = new ResetPwdPresenterImpl(this);
    }

    private void initView() {
        mUsernameEt = (EditText) findViewById(R.id.username_et);
    }

    @Override
    public void showProgressDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("重置密码中");
        }
        mDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void dealResetPwdResult(ResultBean resultBean) {
        if (resultBean.isSuccess()) {
            Toast.makeText(ResetActivity.this, "重置密码为123456", Toast.LENGTH_SHORT).show();
            ActivityUtil.startNew(ResetActivity.this, LoginActivity.class, true);
        } else {
            Toast.makeText(ResetActivity.this, resultBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    public void resetClick(View view) {
        String et = mUsernameEt.getText().toString().trim();
        if (TextUtils.isEmpty(et)) {
            Toast.makeText(ResetActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.resetPwd(et);
    }
}

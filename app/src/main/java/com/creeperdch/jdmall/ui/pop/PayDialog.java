package com.creeperdch.jdmall.ui.pop;
/*
 * Created by CREEPER_D on 2017/8/16.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.listener.IPayClickListener;

public class PayDialog extends Dialog implements View.OnClickListener {

    private final Context mContext;
    private EditText mAccountEt;
    private EditText mPwdEt;
    private EditText mPayPwdEt;
    private Button mCancelBtn;
    private Button mPayBtn;
    private IPayClickListener mListener;

    public void setListener(IPayClickListener listener) {
        this.mListener = listener;
    }

    public PayDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_dialog);
        initView();
    }

    private void initView() {
        mAccountEt = findViewById(R.id.account_et);
        mPwdEt = findViewById(R.id.pwd_et);
        mPayPwdEt = findViewById(R.id.pay_pwd_et);
        mCancelBtn = findViewById(R.id.cancel_btn);
        mPayBtn = findViewById(R.id.pay_btn);
        mCancelBtn.setOnClickListener(this);
        mPayBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.pay_btn:
                String account = mAccountEt.getText().toString();
                String pwd = mPwdEt.getText().toString();
                String payPwd = mPayPwdEt.getText().toString();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(payPwd)) {
                    Toast.makeText(mContext, "请输入支付信息", Toast.LENGTH_SHORT).show();
                }
                if (mListener != null) {
                    mListener.onPayClicked(account, pwd, payPwd);
                }
                break;
        }
    }
}

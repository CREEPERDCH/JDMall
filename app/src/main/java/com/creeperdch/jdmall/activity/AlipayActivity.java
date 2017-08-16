package com.creeperdch.jdmall.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.OrderBean;
import com.creeperdch.jdmall.bean.PayInfoBean;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SMockPayParams;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.AlipayController;
import com.creeperdch.jdmall.listener.IPayClickListener;
import com.creeperdch.jdmall.ui.pop.PayDialog;

public class AlipayActivity extends BaseActivity implements IPayClickListener {

    public static final String TN_KEY = "AlipayActivity::tn";

    private String mTn;
    private TextView mPayPriceTv;
    private TextView mOrderDescValTv;
    private TextView mDealTypeValTv;
    private TextView mDealTimeValTv;
    private TextView mDealNoValTv;
    private PayDialog mPayDialog;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.GET_PAYINFO_ACTION) {
            showPayInfoUI(msg.obj);
        } else if (msg.what == IDiyMessage.MOCK_PAY_ACTION) {
            dealPayResult((ResultBean) msg.obj);
        }
    }

    @SuppressLint("SetTextI18n")
    private void showPayInfoUI(Object obj) {
        if (obj == null) {
            return;
        }
        PayInfoBean info = (PayInfoBean) obj;
        mPayPriceTv.setText("$ " + info.getTotalPrice());
        mOrderDescValTv.setText(info.getPname());
        mDealTypeValTv.setText("担保交易");
        mDealTimeValTv.setText(info.getPayTime());
        mDealNoValTv.setText(info.getOinfo());
    }

    private void dealPayResult(ResultBean resultBean) {
        if (mPayDialog != null && mPayDialog.isShowing()) {
            mPayDialog.dismiss();
        }
        if (resultBean.isSuccess()) {
            showToast("支付成功!");
            //解析数据
            OrderBean orderBean = JSON.parseObject(resultBean.getResult(), OrderBean.class);
            //跳转到订单详情页面
            Intent intent = new Intent(this, OrderDetailsActivity.class);
            intent.putExtra(OrderDetailsActivity.OID_KEY, orderBean.getOid());
            startActivity(intent);
            finish();
        } else {
            showToast(resultBean.getErrorMsg());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        initView();
        initData();
        initController();
        mController.sendAsyncMessage(IDiyMessage.GET_PAYINFO_ACTION, mTn, JDMallApplication.getUserId());
    }

    @Override
    protected void initView() {
        mPayPriceTv = findViewById(R.id.pay_price_tv);
        mOrderDescValTv = findViewById(R.id.order_desc_val_tv);
        mDealTypeValTv = findViewById(R.id.deal_type_val_tv);
        mDealTimeValTv = findViewById(R.id.deal_time_val_tv);
        mDealNoValTv = findViewById(R.id.deal_no_val_tv);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mTn = intent.getStringExtra(TN_KEY);
        if (mTn == null) {
            showToast("data error!");
            finish();
        }
    }

    @Override
    protected void initController() {
        mController = new AlipayController(this);
        mController.setListener(this);
    }

    public void payClick(View view) {
        if (mPayDialog == null) {
            mPayDialog = new PayDialog(this);
            mPayDialog.setListener(this);
        }
        mPayDialog.show();
    }

    @Override
    public void onPayClicked(String account, String pwd, String payPwd) {
        SMockPayParams params = new SMockPayParams(account, pwd, payPwd, mTn, JDMallApplication.getUserId());
        mController.sendAsyncMessage(IDiyMessage.MOCK_PAY_ACTION, params);
    }
}

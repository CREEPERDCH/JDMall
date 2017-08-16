package com.creeperdch.jdmall.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.OrderProductAdapter;
import com.creeperdch.jdmall.bean.OrderDetailsBean;
import com.creeperdch.jdmall.bean.OrderProductBean;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.consf.OrderStatus;
import com.creeperdch.jdmall.controller.OrderController;
import com.creeperdch.jdmall.utils.FixedViewUtil;

import java.util.List;

public class OrderDetailsActivity extends BaseActivity {

    public static final String OID_KEY = "OrderDetailsActivity::oid";

    private TextView mOrderNOTv;
    private TextView mStatusTv;
    private TextView mReceiveNameTv;
    private TextView mReceivePhoneTv;
    private TextView mReceiveAddressTv;
    private ListView mProductsLv;
    private TextView mTotalPriceValTv;
    private TextView mTakePriceValTv;
    private TextView mActualPriceValTv;
    private TextView mOrderTimeTv;
    private OrderProductAdapter mAdapter;
    private long mOid;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.ORDER_DETAILS_ACTION) {
            showOrderDetails(msg.obj);
        }
    }

    @SuppressLint("SetTextI18n")
    private void showOrderDetails(Object obj) {
        if (obj == null) {
            return;
        }
        OrderDetailsBean bean = (OrderDetailsBean) obj;
        mOrderNOTv.setText("订单编号:" + bean.getOrderNum());
        mStatusTv.setText(OrderStatus.getOrderStatus(bean.getStatus()));
        showReceiverAddress(bean.getAddress());
        showOrderProducts(bean.getItems());
        mTotalPriceValTv.setText("$ " + (bean.getTotalPrice() - bean.getFreight()));
        mTakePriceValTv.setText("$ " + bean.getFreight());
        mActualPriceValTv.setText("$ " + bean.getTotalPrice());//用户应该付款的价格
        mOrderTimeTv.setText("下单时间:" + bean.getBuyTime());
    }

    private void showReceiverAddress(String addressJson) {
        ReceiverAddress bean = JSON.parseObject(addressJson, ReceiverAddress.class);
        mReceiveNameTv.setText(bean.getReceiverName());
        mReceivePhoneTv.setText(bean.getReceiverPhone());
        mReceiveAddressTv.setText(bean.getReceiverAddress());
    }

    private void showOrderProducts(String productsJson) {
        List<OrderProductBean> datas = JSON.parseArray(productsJson, OrderProductBean.class);
        mAdapter.setDatas(datas);
        mAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mProductsLv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initData();
        initView();
        initController();
        mController.sendAsyncMessage(IDiyMessage.ORDER_DETAILS_ACTION, JDMallApplication.getUserId(), mOid);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mOid = intent.getLongExtra(OID_KEY, 0);
        if (mOid == 0) {
            showToast("data error!");
            finish();
        }
    }

    @Override
    protected void initView() {
        mOrderNOTv = findViewById(R.id.order_no_tv);
        mStatusTv = findViewById(R.id.status_tv);
        mReceiveNameTv = findViewById(R.id.receive_name_tv);
        mReceivePhoneTv = findViewById(R.id.receive_phone_tv);
        mReceiveAddressTv = findViewById(R.id.receive_address_tv);
        mProductsLv = findViewById(R.id.products_lv);

        mAdapter = new OrderProductAdapter(this);
        mProductsLv.setAdapter(mAdapter);

        mTotalPriceValTv = findViewById(R.id.total_price_val_tv);
        mTakePriceValTv = findViewById(R.id.take_price_val_tv);
        mActualPriceValTv = findViewById(R.id.actual_price_val_tv);
        mOrderTimeTv = findViewById(R.id.order_time_tv);
    }

    @Override
    protected void initController() {
        mController = new OrderController(this);
        mController.setListener(this);
    }
}

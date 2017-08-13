package com.creeperdch.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.AddressListAdapter;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.consf.ReceiverAddressConstant;
import com.creeperdch.jdmall.controller.AddressController;
import com.creeperdch.jdmall.listener.IAddressDelListener;
import com.creeperdch.jdmall.ui.FlexiListView;

import java.util.List;

public class AddressListActivity extends BaseActivity implements IAddressDelListener, AdapterView.OnItemClickListener {
    private FlexiListView mAddressLv;
    private AddressListAdapter mAddressListAdapter;
    //如果是我的JD页面进来的标识是1 否则是0
    private int mSign;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.ADDRESS_LIST_ACTION:
                showAddressLv((List<ReceiverAddress>) msg.obj);
                break;
            case IDiyMessage.DEL_ADDRESS_ACTION:
                dealDelAddressResult((ResultBean) msg.obj);
                break;
        }
    }

    private void showAddressLv(List<ReceiverAddress> datas) {
        mAddressListAdapter.setDatas(datas);
        mAddressListAdapter.notifyDataSetChanged();
    }

    private void dealDelAddressResult(ResultBean resultBean) {
        showToast(resultBean.isSuccess() ? "删除成功" : resultBean.getErrorMsg());
        if (resultBean.isSuccess()) {
            requestAddressData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initData();
        initView();
        initController();
        requestAddressData();
    }

    @Override
    protected void initData() {
        mSign = getIntent().getIntExtra(ReceiverAddressConstant.START_SIGN_KEY, 0);
    }

    @Override
    protected void initView() {
        mAddressLv = findViewById(R.id.address_lv);
        mAddressListAdapter = new AddressListAdapter(this);
        mAddressListAdapter.setListener(this);
        mAddressLv.setAdapter(mAddressListAdapter);
        if (mSign != 1) {
            mAddressLv.setOnItemClickListener(this);
        }
    }


    @Override
    protected void initController() {
        mController = new AddressController(this);
        mController.setListener(this);
    }

    private void requestAddressData() {
        JDMallApplication application = (JDMallApplication) getApplication();
        mController.sendAsyncMessage(IDiyMessage.ADDRESS_LIST_ACTION, application.mUserInfo.getId());
    }

    @Override
    public void onAddressDeleted(long addressId) {
        JDMallApplication application = (JDMallApplication) getApplication();
        mController.sendAsyncMessage(IDiyMessage.DEL_ADDRESS_ACTION, application.mUserInfo.getId(), addressId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReceiverAddress bean = mAddressListAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(ReceiverAddressConstant.ADDRESS_KEY, bean);
        setResult(0, intent);
        finish();
    }
}

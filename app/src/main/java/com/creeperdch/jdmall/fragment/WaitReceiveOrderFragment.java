package com.creeperdch.jdmall.fragment;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.WaitReceiveAdapter;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.consf.OrderStatus;

public class WaitReceiveOrderFragment extends BaseOrderFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_receive, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initComponent(view, R.id.wait_receive_order_lv, WaitReceiveAdapter.class, R.id.wait_receive_null_view);
    }

    @Override
    public void onRefresh() {
        mController.sendAsyncMessage(IDiyMessage.ORDER_LIST_ACTION, JDMallApplication.getUserId(), OrderStatus.WAIT_RECEIVE_ORDER);
    }
}

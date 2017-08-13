package com.creeperdch.jdmall.fragment;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.creeperdch.jdmall.adapter.OrderListBaseAdapter;
import com.creeperdch.jdmall.bean.OrderListBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.OrderController;
import com.creeperdch.jdmall.ui.xlistview.XListView;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseOrderFragment extends BaseFragment implements XListView.IXListViewListener {
    protected XListView mListView;
    protected OrderListBaseAdapter mAdapter;
    protected View mNullView;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.ORDER_LIST_ACTION) {
            showOrderList((List<OrderListBean>) msg.obj);
        }
    }

    private void showOrderList(List<OrderListBean> datas) {
        mAdapter.setDatas(datas);
        mAdapter.notifyDataSetChanged();
        mListView.stopRefresh();
        mListView.setVisibility(!datas.isEmpty() ? View.VISIBLE : View.GONE);
        mNullView.setVisibility(datas.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        onRefresh();
    }

    @Override
    protected void initController() {
        mController = new OrderController(getActivity());
        mController.setListener(this);
    }

    protected String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatter.format(new Date());
    }


    @Override
    public void onLoadMore() {

    }

    protected void initComponent(View containerView, int listViewId, Class<? extends OrderListBaseAdapter> adapterClazz, int nullViewId) {
        mListView = containerView.findViewById(listViewId);
        mListView.setRefreshTime(getCurrentTime());
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(false);
        mListView.setXListViewListener(this);
        /*
          1.拿到构造器
          2.根据构造器创建对象
         */
        try {
            Constructor<? extends OrderListBaseAdapter> constructor = adapterClazz.getDeclaredConstructor(Context.class);
            mAdapter = constructor.newInstance(getActivity());
            mListView.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNullView = containerView.findViewById(nullViewId);
    }
}

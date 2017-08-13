package com.creeperdch.jdmall.fragment;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.SettleActivity;
import com.creeperdch.jdmall.adapter.ShopcarAdapter;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.ShopCarBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.ShopCarController;
import com.creeperdch.jdmall.listener.IShopcarDeleteListener;
import com.creeperdch.jdmall.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class ShopCarFragment extends BaseFragment implements IShopcarDeleteListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView mShopcarLv;
    private CheckBox mAllCbx;
    private TextView mAllMoneyTv;
    private TextView mSettleTv;
    private ShopcarAdapter mAdapter;
    private long mDelShopcarId;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.SHOPCAR_LIST_ACTION:
                showShopCars((List<ShopCarBean>) msg.obj);
                break;
            case IDiyMessage.DEL_SHOPCAR_ITEM:
                showDelShopcarsResult((ResultBean) msg.obj);
                break;
        }
    }

    private void showShopCars(List<ShopCarBean> datas) {
        mAdapter.setDatas(datas);
        mAdapter.notifyDataSetChanged();
    }

    private void showDelShopcarsResult(ResultBean resultBean) {
        if (resultBean.isSuccess()) {
            showToast("商品删除成功");
            if (mDelShopcarId != 0) {
                mAdapter.delItem(mDelShopcarId);
                mDelShopcarId = 0;
            }
            resetCheckProductInfo();
        } else {
            showToast(resultBean.getErrorMsg());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_shopcar, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        JDMallApplication app = (JDMallApplication) getActivity().getApplication();
        mController.sendAsyncMessage(IDiyMessage.SHOPCAR_LIST_ACTION, app.mUserInfo.getId());
    }

    @Override
    protected void initController() {
        mController = new ShopCarController(getActivity());
        mController.setListener(this);
    }

    private void initView(View view) {
        mShopcarLv = (ListView) view.findViewById(R.id.shopcar_lv);
        mAdapter = new ShopcarAdapter(getActivity());
        mAdapter.setListener(this);
        mShopcarLv.setAdapter(mAdapter);
        mShopcarLv.setOnItemClickListener(this);

        mAllCbx = (CheckBox) view.findViewById(R.id.all_cbx);
        mAllCbx.setOnClickListener(this);
        mAllMoneyTv = (TextView) view.findViewById(R.id.all_money_tv);
        mSettleTv = (TextView) view.findViewById(R.id.settle_tv);
        mSettleTv.setOnClickListener(this);
    }

    @Override
    public void onShopcarDeleted(long shopcarId) {
        JDMallApplication app = (JDMallApplication) getActivity().getApplication();
        mController.sendAsyncMessage(IDiyMessage.DEL_SHOPCAR_ITEM, app.mUserInfo.getId(), shopcarId);
        mDelShopcarId = shopcarId;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.tabItem(position);
        mAllCbx.setChecked(mAdapter.isAllChecked());
        resetCheckProductInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //结算按钮
            case R.id.settle_tv:
                Intent intent = new Intent(getActivity(), SettleActivity.class);
                //1.获取选中的商品队列==总数
                ArrayList<ShopCarBean> checkedListData = mAdapter.getCheckedListData();
                //2.获取选中商品的总价
                double totalPrice = mAdapter.getCheckedTotalPrice();
                intent.putExtra(SettleActivity.CHECKDATAS_KEY, checkedListData);
                intent.putExtra(SettleActivity.TOTALPRICE_KEY, totalPrice);
                startActivity(intent);
                break;
            case R.id.all_cbx:
                mAdapter.checkAll(mAllCbx.isChecked());
                resetCheckProductInfo();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void resetCheckProductInfo() {
        mAllMoneyTv.setText("总额: $ " + mAdapter.getCheckedTotalPrice());
        mSettleTv.setText("去结算(" + mAdapter.getCheckedTotalCount() + ")");
    }
}

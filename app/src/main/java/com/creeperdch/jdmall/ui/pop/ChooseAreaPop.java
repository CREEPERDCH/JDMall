package com.creeperdch.jdmall.ui.pop;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.AreaAdapter;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.AddressController;
import com.creeperdch.jdmall.listener.IChooseAreaCompleteListener;
import com.creeperdch.jdmall.listener.IModelChangedListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseAreaPop extends JDBasePop implements IModelChangedListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView mProvinceLv;
    private ListView mCityLv;
    private ListView mAreaLv;
    private AreaAdapter mProvinceAdapter;
    private AreaAdapter mCityAdapter;
    private AreaAdapter mAreaAdapter;
    private AddressController.Area mTabProvince;
    private AddressController.Area mTabCity;
    private AddressController.Area mTabArea;

    private AddressController mController;
    private TextView mTipTv;

    private TextView mSubmitTv;
    private IChooseAreaCompleteListener mListener;

    public void setListener(IChooseAreaCompleteListener listener) {
        this.mListener = listener;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.PROVINCE_ACTION:
                    showProvinceLv((List<AddressController.Area>) msg.obj);
                    break;
                case IDiyMessage.CITY_ACTION:
                    showCityLv((List<AddressController.Area>) msg.obj);
                    break;
                case IDiyMessage.AREA_ACTION:
                    showAreaLv((List<AddressController.Area>) msg.obj);
                    break;
            }
        }
    };

    private void showProvinceLv(List<AddressController.Area> datas) {
        mProvinceAdapter.setDatas(datas);
        mProvinceAdapter.notifyDataSetChanged();
    }

    private void showCityLv(List<AddressController.Area> datas) {
        mCityAdapter.setDatas(datas);
        mCityAdapter.notifyDataSetChanged();
    }

    private void showAreaLv(List<AddressController.Area> datas) {
        mAreaAdapter.setDatas(datas);
        mAreaAdapter.notifyDataSetChanged();
    }

    public ChooseAreaPop(Context context) {
        super(context);
        initController();
        requestProvinceData();
    }

    private void initController() {
        mController = new AddressController(mContext);
        mController.setListener(this);
    }

    private void requestProvinceData() {
        mController.sendAsyncMessage(IDiyMessage.PROVINCE_ACTION);
    }

    @Override
    protected void initViews() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.address_pop_view, null);
        initViews(contentView);
        mPopupWindow = new PopupWindow(contentView, -1, -1);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //刷新当前页面的所有设置
        mPopupWindow.update();
    }

    private void initViews(View rootView) {
        mProvinceLv = (ListView) rootView.findViewById(R.id.province_lv);
        mProvinceAdapter = new AreaAdapter(mContext);
        mProvinceLv.setAdapter(mProvinceAdapter);
        mProvinceLv.setOnItemClickListener(this);

        mCityLv = (ListView) rootView.findViewById(R.id.city_lv);
        mCityAdapter = new AreaAdapter(mContext);
        mCityLv.setAdapter(mCityAdapter);
        mCityLv.setOnItemClickListener(this);

        mAreaLv = (ListView) rootView.findViewById(R.id.area_lv);
        mAreaAdapter = new AreaAdapter(mContext);
        mAreaLv.setAdapter(mAreaAdapter);
        mAreaLv.setOnItemClickListener(this);

        mTipTv = (TextView) rootView.findViewById(R.id.tip_tv);
        mSubmitTv = (TextView) rootView.findViewById(R.id.submit_tv);
        mSubmitTv.setOnClickListener(this);
    }

    @Override
    public void onShow(View anchorView) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void onModelChanged(int action, Object returnBean) {
        mHandler.obtainMessage(action, returnBean).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.province_lv:
                requestCityData(mProvinceAdapter.getItem(position).getCode());
                showAreaLv(new ArrayList<AddressController.Area>());
                mTabProvince = mProvinceAdapter.getItem(position);
                mTabCity = null;
                mTabArea = null;
                break;
            case R.id.city_lv:
                requestAreaData(mCityAdapter.getItem(position).getCode());
                mTabCity = mCityAdapter.getItem(position);
                mTabArea = null;
                break;
            case R.id.area_lv:
                mTabArea = mAreaAdapter.getItem(position);
                break;
        }
        showCurrentTabInfo();
    }

    private void showCurrentTabInfo() {
        String tipStr = "";
        if (mTabProvince != null) {
            tipStr += mTabProvince.getName();
        }
        if (mTabCity != null) {
            tipStr += mTabCity.getName();
        }
        if (mTabArea != null) {
            tipStr += mTabArea.getName();
        }
        mTipTv.setText(tipStr);
    }

    private void requestAreaData(String cityCode) {
        mController.sendAsyncMessage(IDiyMessage.AREA_ACTION, cityCode);
    }

    private void requestCityData(String provinceCode) {
        mController.sendAsyncMessage(IDiyMessage.CITY_ACTION, provinceCode);
    }

    @Override
    public void onClick(View v) {
        if (mTabProvince == null || mTabCity == null || mTabArea == null) {
            Toast.makeText(mContext, "请选择完整的地址信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mListener != null) {
            mListener.onAreaChosen(mTabProvince, mTabCity, mTabArea);
        }
    }
}

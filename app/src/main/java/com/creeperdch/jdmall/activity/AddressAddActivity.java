package com.creeperdch.jdmall.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.ChosenArea;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SAddAddressParams;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.AddressController;
import com.creeperdch.jdmall.listener.IChooseAreaCompleteListener;
import com.creeperdch.jdmall.ui.pop.ChooseAreaPop;

import static com.creeperdch.jdmall.R.id.name_et;

public class AddressAddActivity extends BaseActivity implements View.OnClickListener, IChooseAreaCompleteListener {

    private EditText mNameEt;
    private EditText mPhoneEt;
    private TextView mChoseAreaBtn;
    private EditText mAddressDetailsEt;
    private CheckBox mDefaultCbx;
    private LinearLayout mParentView;
    private ChooseAreaPop mChooseAreaPop;
    private ChosenArea mChosenArea;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.ADD_ADDRESS_ACTION:
                ResultBean resultBean = (ResultBean) msg.obj;
                if (!resultBean.isSuccess()) {
                    showToast(resultBean.getErrorMsg());
                    return;
                }
                showToast("添加地址成功!");
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        initController();
        initView();
    }

    @Override
    protected void initController() {
        mController = new AddressController(this);
        mController.setListener(this);
    }

    @Override
    protected void initView() {
        mNameEt = (EditText) findViewById(name_et);
        mPhoneEt = (EditText) findViewById(R.id.phone_et);
        mChoseAreaBtn = (TextView) findViewById(R.id.choose_area_tv);
        mAddressDetailsEt = (EditText) findViewById(R.id.address_details_et);
        mDefaultCbx = (CheckBox) findViewById(R.id.default_cbx);
        mParentView = (LinearLayout) findViewById(R.id.parent_view);
        mChoseAreaBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mChooseAreaPop == null) {
            mChooseAreaPop = new ChooseAreaPop(this);
            mChooseAreaPop.setListener(this);
        }
        mChooseAreaPop.onShow(findViewById(R.id.parent_view));
    }

    public void saveAddress(View view) {
        String name = mNameEt.getText().toString().trim();
        String phone = mPhoneEt.getText().toString().trim();
        String addressDetails = mAddressDetailsEt.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(addressDetails)) {
            showToast("请输入完整的地址信息");
            return;
        }
        if (mChosenArea == null) {
            showToast("请选择完整的地址信息");
            return;
        }
        boolean isDefault = mDefaultCbx.isChecked();
        SAddAddressParams params = buildAddAddressSendParams(name, phone, mChosenArea, addressDetails, isDefault);
        mController.sendAsyncMessage(IDiyMessage.ADD_ADDRESS_ACTION, params);
    }

    private SAddAddressParams buildAddAddressSendParams(String name, String phone, ChosenArea mChosenArea, String addressDetails, boolean
            isDefault) {
        JDMallApplication application = (JDMallApplication) getApplication();
        return new SAddAddressParams(application.mUserInfo.getId(), name, phone, mChosenArea.tabProvince.getCode(), mChosenArea.tabCity
                .getCode(), mChosenArea.tabArea.getCode(), addressDetails, isDefault);
    }

    @Override
    public void onAreaChosen(AddressController.Area tabProvince, AddressController.Area tabCity, AddressController.Area tabArea) {
        //弹出框确认按钮点击的时候调用的方法
        if (mChooseAreaPop != null) {
            mChooseAreaPop.onDismiss();
        }
        mChoseAreaBtn.setText(tabProvince.getName() + tabCity.getName() + tabArea.getName());
        mChosenArea = new ChosenArea(tabProvince, tabCity, tabArea);
    }
}

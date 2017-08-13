package com.creeperdch.jdmall.ui.pop;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.AddressAddActivity;
import com.creeperdch.jdmall.activity.AddressListActivity;
import com.creeperdch.jdmall.utils.ActivityUtil;

/*
 * Created by CREEPER_D on 2017/8/10.
 */

public class AddressChooseDialog extends Dialog implements View.OnClickListener {

    private final Context mContext;

    private Button mChooseAddressId;
    private Button mAddAddressId;

    public AddressChooseDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_choose_dialog);
        initUI();
    }

    private void initUI() {
        mChooseAddressId = (Button) findViewById(R.id.choose_address_id);
        mAddAddressId = (Button) findViewById(R.id.add_address_id);

        mChooseAddressId.setOnClickListener(this);
        mAddAddressId.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.choose_address_id:
                ActivityUtil.startNew(mContext, AddressListActivity.class, false);
                break;
            case R.id.add_address_id:
                ActivityUtil.startNew(mContext, AddressAddActivity.class, false);
                break;
        }
    }
}

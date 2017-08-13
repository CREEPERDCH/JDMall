package com.creeperdch.jdmall.fragment;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.LoginActivity;
import com.creeperdch.jdmall.activity.OrderListActivity;
import com.creeperdch.jdmall.bean.LoginBean;
import com.creeperdch.jdmall.consf.HttpConstant;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.UserController;
import com.creeperdch.jdmall.ui.pop.AddressChooseDialog;
import com.creeperdch.jdmall.utils.ActivityUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyJDFragment extends BaseFragment implements View.OnClickListener {

    private CircleImageView mUserIconIv;
    private TextView mUserNameTv;
    private TextView mUserLevelTv;
    private TextView mWaitPayTv;
    private TextView mWaitReceiveTv;
    private Button mLogoutBtn;
    private LinearLayout mAddressId;
    private AddressChooseDialog mAddressChooseDialog;
    private LinearLayout mMimeOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_myjd, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillUserInfo();
        initController();
    }

    private void fillUserInfo() {
        JDMallApplication app = (JDMallApplication) getActivity().getApplication();
        LoginBean userInfo = app.mUserInfo;
        if (userInfo != null) {
            mUserNameTv.setText(userInfo.getUserName());
            mUserLevelTv.setText(userInfo.getUserLevelStr());
            mWaitPayTv.setText(userInfo.getWaitPayCount() + "");
            mWaitReceiveTv.setText(userInfo.getWaitReceiveCount() + "");
            Glide.with(this).load(HttpConstant.BASE_URL + userInfo.getUserIcon()).into(mUserIconIv);
        }
    }

    @Override
    protected void initController() {
        mController = new UserController(getActivity());
        mController.setListener(this);
    }

    private void initView(View view) {
        mUserIconIv = view.findViewById(R.id.user_icon_iv);
        mUserNameTv = view.findViewById(R.id.user_name_tv);
        mUserLevelTv = view.findViewById(R.id.user_level_tv);
        mWaitPayTv = view.findViewById(R.id.wait_pay_tv);
        mWaitReceiveTv = view.findViewById(R.id.wait_receive_tv);
        mLogoutBtn = view.findViewById(R.id.logout_btn);
        mAddressId = view.findViewById(R.id.address_id);
        mMimeOrder = view.findViewById(R.id.mime_order);

        mWaitPayTv.setOnClickListener(this);
        mWaitReceiveTv.setOnClickListener(this);
        mLogoutBtn.setOnClickListener(this);
        mAddressId.setOnClickListener(this);
        mMimeOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wait_pay_tv:
                break;
            case R.id.wait_receive_tv:
                break;
            case R.id.logout_btn:
                logout();
                break;
            case R.id.address_id:
                if (mAddressChooseDialog == null) {
                    mAddressChooseDialog = new AddressChooseDialog(getActivity());
                }
                mAddressChooseDialog.show();
                break;
            case R.id.mime_order:
                ActivityUtil.startNew(getActivity(), OrderListActivity.class, false);
                break;
        }
    }

    private void logout() {
        mController.sendAsyncMessage(IDiyMessage.CLEAR_ACCOUNT_ACTION);
        ActivityUtil.startNew(getActivity(), LoginActivity.class, true);
    }
}

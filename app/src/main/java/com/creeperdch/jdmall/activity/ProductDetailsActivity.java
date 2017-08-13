package com.creeperdch.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.creeperdch.jdmall.JDMallApplication;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.ProductContainerAdapter;
import com.creeperdch.jdmall.bean.ResultBean;
import com.creeperdch.jdmall.bean.SAdd2ShopcarParams;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.ProductDetailsController;
import com.creeperdch.jdmall.listener.OnPageChangeListenerAdapter;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {

    public static final String PID_KEY = "ProductDetailsActivity::pid";

    public long mPid;
    public int mBuyCount;
    public String mPVersion;
    private View mDetailsView;
    private LinearLayout mDetailsLl;
    private View mIntroduceView;
    private LinearLayout mIntroduceLl;
    private View mCommentTv;
    private LinearLayout mCommentLl;
    private ViewPager mContainerVp;

    @Override
    protected void handleUI(Message msg) {
        if (msg.what == IDiyMessage.ADD2SHOPCAR_ACTION) {
            ResultBean resultBean = (ResultBean) msg.obj;
            if (resultBean.isSuccess()) {
                showToast("加入购物车成功!");
                finish();
            } else {
                showToast("加入购物车失败: " + resultBean.getErrorMsg());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initData();
        initController();
        initView();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mPid = intent.getLongExtra(PID_KEY, 0);
        if (mPid == 0) {
            showToast("数据异常!");
            finish();
        }
    }

    @Override
    protected void initController() {
        mController = new ProductDetailsController(this);
        mController.setListener(this);
    }

    @Override
    protected void initView() {
        mDetailsView = (View) findViewById(R.id.details_view);
        mDetailsLl = (LinearLayout) findViewById(R.id.details_ll);
        mDetailsLl.setOnClickListener(this);

        mIntroduceView = (View) findViewById(R.id.introduce_view);
        mIntroduceLl = (LinearLayout) findViewById(R.id.introduce_ll);
        mIntroduceLl.setOnClickListener(this);

        mCommentTv = (View) findViewById(R.id.comment_tv);
        mCommentLl = (LinearLayout) findViewById(R.id.comment_ll);
        mCommentLl.setOnClickListener(this);

        mContainerVp = (ViewPager) findViewById(R.id.vp);
        mContainerVp.setAdapter(new ProductContainerAdapter(getSupportFragmentManager()));
        mContainerVp.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        changeIndicator(mIntroduceView);
                        break;
                    case 1:
                        changeIndicator(mDetailsView);
                        break;
                    case 2:
                        changeIndicator(mCommentTv);
                        break;
                }
            }
        });

    }

    private void changeIndicator(View view) {
        mIntroduceView.setVisibility(View.INVISIBLE);
        mDetailsView.setVisibility(View.INVISIBLE);
        mCommentTv.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.introduce_ll:
                mContainerVp.setCurrentItem(0, true);
                break;
            case R.id.details_ll:
                mContainerVp.setCurrentItem(1, true);
                break;
            case R.id.comment_ll:
                mContainerVp.setCurrentItem(2, true);
                break;
        }
    }

    public void add2ShopCar(View view) {
        if (mPVersion == null) {
            showToast("请选择购买的型号");
            return;
        }
        if (mBuyCount == 0) {
            showToast("请选择需要购买的商品数量");
            return;
        }
        JDMallApplication app = (JDMallApplication) getApplication();
        SAdd2ShopcarParams params = new SAdd2ShopcarParams(app.mUserInfo.getId(), mPid, mBuyCount, mPVersion);
        mController.sendAsyncMessage(IDiyMessage.ADD2SHOPCAR_ACTION, params);
    }
}

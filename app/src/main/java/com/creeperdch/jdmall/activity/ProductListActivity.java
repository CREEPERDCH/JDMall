package com.creeperdch.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.BrandAdapter;
import com.creeperdch.jdmall.adapter.ProductListAdapter;
import com.creeperdch.jdmall.adapter.RProductList;
import com.creeperdch.jdmall.bean.Brand;
import com.creeperdch.jdmall.bean.SProductListParams;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.ProductListController;
import com.creeperdch.jdmall.listener.IProductSorChangeListener;
import com.creeperdch.jdmall.ui.pop.ProductSortPop;
import com.creeperdch.jdmall.utils.FixedViewUtil;

import java.util.List;

public class ProductListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        IProductSorChangeListener {

    public static final String ThirdCategoryId = "ProductListActivity::ThirdCategoryId";
    public static final String TopCategoryId = "ProductListActivity::TopCategoryId";

    private long mThirdCategoryId;
    private long mTopCategoryId;
    private TextView mJdTakeTv;
    private TextView mPaywhenreceiveTv;
    private TextView mJusthasstockTv;
    private GridView mBrandGv;
    private BrandAdapter mBrandAdapter;
    private TextView mAllIndicator;
    private TextView mSaleIndicator;
    private TextView mPriceIndicator;
    private TextView mChooseIndicator;
    private ListView mProductLv;
    private DrawerLayout mContainerDl;
    private View mSlideView;
    private ProductSortPop mProductSortPop;
    private SProductListParams mSendParams;
    private ProductListAdapter mProductListAdapter;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.BRAND_ACTION:
                showBrandModule((List<Brand>) msg.obj);
                break;
            case IDiyMessage.PRODUCT_LIST_ACTION:
                showProductList((List<RProductList>) msg.obj);
                break;
        }
    }

    private void showBrandModule(List<Brand> datas) {
        mBrandAdapter.setDatas(datas);
        mBrandAdapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mBrandGv, 3);
    }

    private void showProductList(List<RProductList> datas) {
        mProductListAdapter.setDatas(datas);
        mProductListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initView();
        initData();
        initController();
        mController.sendAsyncMessage(IDiyMessage.BRAND_ACTION, mTopCategoryId);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
    }

    @Override
    protected void initController() {
        mController = new ProductListController(this);
        mController.setListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mThirdCategoryId = intent.getLongExtra(ThirdCategoryId, 0);
        mTopCategoryId = intent.getLongExtra(TopCategoryId, 0);
        if (mThirdCategoryId == 0 || mTopCategoryId == 0) {
            showToast("数据异常!");
            finish();
        }
        mSendParams = new SProductListParams(mThirdCategoryId, 0, SProductListParams.FILTER_ALL, SProductListParams.SORT_NONE,
                SProductListParams.DELIVER_NONE);
    }

    @Override
    protected void initView() {
        /*----------------框架的组件--------------------*/
        mContainerDl = (DrawerLayout) findViewById(R.id.drawerlayout);
        mSlideView = findViewById(R.id.slide_view);
        /*----------------侧滑页面的代码--------------------*/
        mJdTakeTv = (TextView) findViewById(R.id.jd_take_tv);
        mPaywhenreceiveTv = (TextView) findViewById(R.id.paywhenreceive_tv);
        mJusthasstockTv = (TextView) findViewById(R.id.justhasstock_tv);
        mJdTakeTv.setOnClickListener(this);
        mPaywhenreceiveTv.setOnClickListener(this);
        mJusthasstockTv.setOnClickListener(this);
        mBrandGv = (GridView) findViewById(R.id.gv);
        mBrandAdapter = new BrandAdapter(this);
        mBrandGv.setAdapter(mBrandAdapter);
        mBrandGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBrandAdapter.tabItem(position);
            }
        });
        /*----------------主页面的代码--------------------*/
        mAllIndicator = (TextView) findViewById(R.id.all_indicator);
        mAllIndicator.setOnClickListener(this);
        mSaleIndicator = (TextView) findViewById(R.id.sale_indicator);
        mSaleIndicator.setOnClickListener(this);
        mPriceIndicator = (TextView) findViewById(R.id.price_indicator);
        mPriceIndicator.setOnClickListener(this);
        mChooseIndicator = (TextView) findViewById(R.id.choose_indicator);
        mChooseIndicator.setOnClickListener(this);
        mProductLv = (ListView) findViewById(R.id.product_lv);
        mProductListAdapter = new ProductListAdapter(this);
        mProductLv.setAdapter(mProductListAdapter);
        mProductLv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jd_take_tv:
            case R.id.paywhenreceive_tv:
            case R.id.justhasstock_tv:
                v.setSelected(!v.isSelected());
                break;
            case R.id.all_indicator:
                if (mProductSortPop == null) {
                    mProductSortPop = new ProductSortPop(this);
                    mProductSortPop.setListener(this);
                }
                mProductSortPop.onShow(v);
                break;
            case R.id.sale_indicator:
                mSendParams.sortType = SProductListParams.SORT_SALE;
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                break;
            case R.id.price_indicator:
                int currentSortType = mSendParams.sortType;
                if (currentSortType == SProductListParams.SORT_NONE || currentSortType == SProductListParams.SORT_SALE || currentSortType
                        == SProductListParams.SORT_PRICE_DOWN) {
                    mSendParams.sortType = SProductListParams.SORT_PRICE_TOP;
                    mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                } else if (currentSortType == SProductListParams.SORT_PRICE_TOP) {
                    mSendParams.sortType = SProductListParams.SORT_PRICE_DOWN;
                    mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                }
                break;
            case R.id.choose_indicator:
                mContainerDl.openDrawer(mSlideView);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.PID_KEY, mProductListAdapter.getItemId(position));
        startActivity(intent);
    }

    @Override
    public void onSortChanged(int viewId) {
        switch (viewId) {
            case R.id.all_sort:
                mAllIndicator.setText("综合");
                //TODO
                mSendParams.filterType = SProductListParams.FILTER_ALL;
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                break;
            case R.id.new_sort:
                mAllIndicator.setText("新品");
                //TODO
                mSendParams.filterType = SProductListParams.FILTER_NEW;
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                break;
            case R.id.comment_sort:
                mAllIndicator.setText("评价");
                //TODO
                mSendParams.filterType = SProductListParams.FILTER_COMMENT;
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
                break;
        }
    }

    /**
     * 确定
     */
    public void chooseSearchClick(View view) {
        mContainerDl.closeDrawer(mSlideView);
        int deliverResult = SProductListParams.DELIVER_NONE;
        if (mJdTakeTv.isSelected()) {
            deliverResult += SProductListParams.DELIVER_JDDELIVER;
        }
        if (mPaywhenreceiveTv.isSelected()) {
            deliverResult += SProductListParams.DELIVER_PAYWHENRECEIVE;
        }
        if (mJusthasstockTv.isSelected()) {
            deliverResult += SProductListParams.DELIVER_HAS_STOCK;
        }
        long brandId = mBrandAdapter.getCheckBrandId();
        mSendParams = new SProductListParams(mThirdCategoryId, brandId, SProductListParams.FILTER_ALL, SProductListParams.SORT_NONE,
                deliverResult);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
    }

    /**
     * 重置
     */
    public void resetClick(View view) {
        mContainerDl.closeDrawer(mSlideView);
        //重置界面
        mJdTakeTv.setSelected(false);
        mPaywhenreceiveTv.setSelected(false);
        mJusthasstockTv.setSelected(false);
        mBrandAdapter.tabItem(-1);
        //发送默认的请求
        mSendParams = new SProductListParams(mThirdCategoryId, 0, SProductListParams.FILTER_ALL, SProductListParams.SORT_NONE,
                SProductListParams.DELIVER_NONE);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mSendParams);
    }
}

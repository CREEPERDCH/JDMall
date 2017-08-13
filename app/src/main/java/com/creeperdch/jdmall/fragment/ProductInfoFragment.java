package com.creeperdch.jdmall.fragment;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.ProductDetailsActivity;
import com.creeperdch.jdmall.adapter.GoodCommentsAdapter;
import com.creeperdch.jdmall.adapter.ProductBannerAdapter;
import com.creeperdch.jdmall.adapter.ProductVersionAdapter;
import com.creeperdch.jdmall.bean.GoodCommentBean;
import com.creeperdch.jdmall.bean.ProductInfoBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.ProductDetailsController;
import com.creeperdch.jdmall.listener.INumberInputListener;
import com.creeperdch.jdmall.listener.OnPageChangeListenerAdapter;
import com.creeperdch.jdmall.ui.NumberInputView;
import com.creeperdch.jdmall.utils.FixedViewUtil;

import java.util.List;

public class ProductInfoFragment extends BaseFragment implements AdapterView.OnItemClickListener, INumberInputListener {

    private ViewPager mProductBannerVp;
    private TextView mProductBannerIndic;
    private ProductBannerAdapter mProductBannerAdapter;
    private TextView mNameTv;
    private TextView mSelfSaleTv;
    private TextView mRecommandProductTv;
    private TextView mPriceTv;
    private TextView mGoodCommentRateTv;
    private TextView mCommentCountTv;
    private TextView mRecommendBuyTv;
    private ListView mProductVersionsLv;
    private ProductVersionAdapter mProductVersionAdapter;
    private NumberInputView mBuyCountEt;
    private ListView mGoodCommentLv;
    private GoodCommentsAdapter mGoodCommentsAdapter;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.PRODUCT_INFO_ACTION:
                loadProductInfo(msg.obj);
                break;
            case IDiyMessage.PRODUCT_GOOD_COMMENT_ACTION:
                loadGoodComment((List<GoodCommentBean>) msg.obj);
                break;
        }
    }

    private void loadProductInfo(Object obj) {
        if (obj == null) {
            return;
        }
        ProductInfoBean bean = (ProductInfoBean) obj;
        showBanner(bean.getImgUrls());
        showProductInfo(bean);
        showProductVersionLv(bean.getTypeList());
    }

    /**
     * 显示广告图
     */
    private void showBanner(String imgUrls) {
        List<String> imgUrlPaths = JSON.parseArray(imgUrls, String.class);
        mProductBannerAdapter = new ProductBannerAdapter(getActivity());
        mProductBannerAdapter.setDatas(imgUrlPaths);
        mProductBannerVp.setAdapter(mProductBannerAdapter);
        mProductBannerIndic.setText("1/" + imgUrlPaths.size());
    }

    /**
     * 显示简单的商品信息
     */
    private void showProductInfo(ProductInfoBean bean) {
        mNameTv.setText(bean.getName());
        mSelfSaleTv.setVisibility(bean.isIfSaleOneself() ? View.VISIBLE : View.GONE);
        mRecommandProductTv.setText(bean.getRecomProduct());
        mPriceTv.setText("¥ " + bean.getPrice());
        mGoodCommentRateTv.setText(bean.getPositiveRate() + "%好评");
        mCommentCountTv.setText(bean.getCommentCount() + "人评价");
        mBuyCountEt.setMax(bean.getStockCount());
    }

    /**
     * 显示商品的型号
     */
    private void showProductVersionLv(String typeList) {
        List<String> pVersions = JSON.parseArray(typeList, String.class);
        mProductVersionAdapter.setDatas(pVersions);
        mProductVersionAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mProductVersionsLv);
    }

    private void loadGoodComment(List<GoodCommentBean> datas) {
        mGoodCommentsAdapter.setDatas(datas);
        mGoodCommentsAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mGoodCommentLv);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_product_info, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_INFO_ACTION, activity.mPid);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_GOOD_COMMENT_ACTION, activity.mPid);
    }

    @Override
    protected void initController() {
        mController = new ProductDetailsController(getActivity());
        mController.setListener(this);
    }

    private void initView(View view) {
        mProductBannerVp = (ViewPager) view.findViewById(R.id.product_banner_vp);
        mProductBannerVp.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                mProductBannerIndic.setText((position + 1) + "/" + mProductBannerAdapter.getCount());
            }
        });

        mProductBannerIndic = (TextView) view.findViewById(R.id.product_banner_indic);
        mNameTv = (TextView) view.findViewById(R.id.name_tv);
        mSelfSaleTv = (TextView) view.findViewById(R.id.self_sale_tv);
        mRecommandProductTv = (TextView) view.findViewById(R.id.desc_tv);
        mPriceTv = (TextView) view.findViewById(R.id.price_tv);
        mGoodCommentRateTv = (TextView) view.findViewById(R.id.good_rate_tv);
        mCommentCountTv = (TextView) view.findViewById(R.id.good_comment_tv);

        mRecommendBuyTv = (TextView) view.findViewById(R.id.recommend_buy_tv);
        mRecommendBuyTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mProductVersionsLv = (ListView) view.findViewById(R.id.product_versions_lv);
        mProductVersionAdapter = new ProductVersionAdapter(getActivity());
        mProductVersionsLv.setAdapter(mProductVersionAdapter);
        mProductVersionsLv.setOnItemClickListener(this);
        mBuyCountEt = (NumberInputView) view.findViewById(R.id.buy_number_et);
        mBuyCountEt.setListener(this);

        mGoodCommentLv = (ListView) view.findViewById(R.id.good_comment_lv);
        mGoodCommentsAdapter = new GoodCommentsAdapter(getActivity());
        mGoodCommentLv.setAdapter(mGoodCommentsAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //修改购买的型号的样式
        mProductVersionAdapter.tabItem(position);
        //通知Activity 当前选中的型号是....
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        activity.mPVersion = mProductVersionAdapter.getItem(position);
    }

    @Override
    public void onTextChange(int newestBuyCount) {
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        activity.mBuyCount = newestBuyCount;
    }
}

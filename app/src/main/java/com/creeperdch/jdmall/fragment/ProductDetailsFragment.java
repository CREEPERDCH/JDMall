package com.creeperdch.jdmall.fragment;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.ProductDetailsActivity;
import com.creeperdch.jdmall.consf.HttpConstant;

public class ProductDetailsFragment extends BaseFragment {

    private WebView mProductWv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_product_details, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews(View view) {
        mProductWv = (WebView) view.findViewById(R.id.product_wv);
        mProductWv.getSettings().setJavaScriptEnabled(true);
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        mProductWv.loadUrl(HttpConstant.PRODUCT_DETAILS_URL + "?productId=" + activity.mPid);
    }
}

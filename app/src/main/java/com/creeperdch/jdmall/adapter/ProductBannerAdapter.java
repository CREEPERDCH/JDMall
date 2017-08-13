package com.creeperdch.jdmall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.creeperdch.jdmall.consf.HttpConstant;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class ProductBannerAdapter extends PagerAdapter {

    private List<ImageView> mChilds;
    private Context mContext;

    public ProductBannerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mChilds != null ? mChilds.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = mChilds.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView iv = mChilds.get(position);
        container.removeView(iv);
    }

    public void setDatas(List<String> datas) {
        mChilds = new ArrayList<>(datas.size());
        for (int i = 0; i < datas.size(); i++) {
            mChilds.add(builtItemView(datas.get(i)));
        }
    }

    @NonNull
    private ImageView builtItemView(String urlPath) {
        ImageView iv = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        iv.setLayoutParams(params);
        Glide.with(mContext).load(HttpConstant.BASE_URL + urlPath).into(iv);
        return iv;
    }
}

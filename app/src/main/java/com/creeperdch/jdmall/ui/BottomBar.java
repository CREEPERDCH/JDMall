package com.creeperdch.jdmall.ui;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.listener.IBottomBarItemClickListener;

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private ImageView mFragMainIv;
    private TextView mFragMain;
    private ImageView mFragCategoryIv;
    private TextView mFragCategory;
    private ImageView mFragShopcarIv;
    private TextView mFragShopcar;
    private ImageView mFragMineIv;
    private TextView mFragMine;

    private IBottomBarItemClickListener mListener;

    public void setmListener(IBottomBarItemClickListener listener) {
        this.mListener = listener;
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initEvent();
        initViews();
    }

    private void initEvent() {
        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);
    }

    private void initViews() {
        mFragMainIv = (ImageView) findViewById(R.id.frag_main_iv);
        mFragMain = (TextView) findViewById(R.id.frag_main);
        mFragCategoryIv = (ImageView) findViewById(R.id.frag_category_iv);
        mFragCategory = (TextView) findViewById(R.id.frag_category);
        mFragShopcarIv = (ImageView) findViewById(R.id.frag_shopcar_iv);
        mFragShopcar = (TextView) findViewById(R.id.frag_shopcar);
        mFragMineIv = (ImageView) findViewById(R.id.frag_mine_iv);
        mFragMine = (TextView) findViewById(R.id.frag_mine);

        //模拟用户的点击事件
        findViewById(R.id.frag_main_ll).performClick();
    }

    public void changeIndicator(View view) {
        mFragMainIv.setSelected(view.getId() == R.id.frag_main_ll);
        mFragMain.setSelected(view.getId() == R.id.frag_main_ll);
        mFragCategoryIv.setSelected(view.getId() == R.id.frag_category_ll);
        mFragCategory.setSelected(view.getId() == R.id.frag_category_ll);
        mFragShopcarIv.setSelected(view.getId() == R.id.frag_shopcar_ll);
        mFragShopcar.setSelected(view.getId() == R.id.frag_shopcar_ll);
        mFragMineIv.setSelected(view.getId() == R.id.frag_mine_ll);
        mFragMine.setSelected(view.getId() == R.id.frag_mine_ll);
    }

    @Override
    public void onClick(View v) {
        changeIndicator(v);
        if (mListener != null) {
            mListener.onItemClick(v.getId());
        }
    }
}

package com.creeperdch.jdmall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.fragment.BaseFragment;
import com.creeperdch.jdmall.fragment.CategoryFragment;
import com.creeperdch.jdmall.fragment.HomeFragment;
import com.creeperdch.jdmall.fragment.MyJDFragment;
import com.creeperdch.jdmall.fragment.ShopCarFragment;
import com.creeperdch.jdmall.listener.IBottomBarItemClickListener;
import com.creeperdch.jdmall.ui.BottomBar;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements IBottomBarItemClickListener {

    private BottomBar mBottomBar;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void initView() {
        mFragments.add(new HomeFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new ShopCarFragment());
        mFragments.add(new MyJDFragment());
        changeFragment(mFragments.get(0));
        mBottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        mBottomBar.setmListener(this);
    }

    private void changeFragment(BaseFragment f) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.top_bar, f);
        transaction.commit();
    }

    @Override
    public void onItemClick(int viewId) {
        switch (viewId) {
            case R.id.frag_main_ll:
                changeFragment(mFragments.get(0));
                break;
            case R.id.frag_category_ll:
                changeFragment(mFragments.get(1));
                break;
            case R.id.frag_shopcar_ll:
                changeFragment(mFragments.get(2));
                break;
            case R.id.frag_mine_ll:
                changeFragment(mFragments.get(3));
                break;
        }
    }
}

package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.creeperdch.jdmall.fragment.BaseFragment;
import com.creeperdch.jdmall.fragment.ProductCommentFragment;
import com.creeperdch.jdmall.fragment.ProductDetailsFragment;
import com.creeperdch.jdmall.fragment.ProductInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductContainerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments = new ArrayList<>();

    public ProductContainerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragments.add(new ProductInfoFragment());
        mFragments.add(new ProductDetailsFragment());
        mFragments.add(new ProductCommentFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}

package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.creeperdch.jdmall.fragment.AllOrderFragment;
import com.creeperdch.jdmall.fragment.BaseFragment;
import com.creeperdch.jdmall.fragment.CompleteOrderFragment;
import com.creeperdch.jdmall.fragment.WaitPayOrderFragment;
import com.creeperdch.jdmall.fragment.WaitReceiveOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderListContainerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments = new ArrayList<>();

    public OrderListContainerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragments.add(new AllOrderFragment());
        mFragments.add(new WaitPayOrderFragment());
        mFragments.add(new WaitReceiveOrderFragment());
        mFragments.add(new CompleteOrderFragment());
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

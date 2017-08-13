package com.creeperdch.jdmall.fragment;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.BannerAdapter;
import com.creeperdch.jdmall.adapter.RecommandProductAdapter;
import com.creeperdch.jdmall.adapter.SecondKillAdapter;
import com.creeperdch.jdmall.bean.BannerBean;
import com.creeperdch.jdmall.bean.RecommandProductBean;
import com.creeperdch.jdmall.bean.SecondKillBean;
import com.creeperdch.jdmall.consf.IDiyMessage;
import com.creeperdch.jdmall.controller.HomeController;
import com.creeperdch.jdmall.listener.OnPageChangeListenerAdapter;
import com.creeperdch.jdmall.utils.BannerIndicatorUtil;
import com.creeperdch.jdmall.utils.FixedViewUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment {

    private RelativeLayout mAdRl;
    private ViewPager mAdVp;
    private LinearLayout mAdIndicator;
    private RecyclerView mSecondKillView;
    private GridView mRecommendGv;
    private BannerAdapter mBannerAdapter;
    private SecondKillAdapter mSecondKillAdapter;
    private RecommandProductAdapter mRecommandProductAdapter;
    private Timer mTimer;

    @Override
    protected void handleUI(Message msg) {
        switch (msg.what) {
            case IDiyMessage.BANNER_ACTION:
                handleBannerResult((List<BannerBean>) msg.obj);
                break;
            case IDiyMessage.SECOND_KILL_ACTION:
                handleSeckillResult((List<SecondKillBean>) msg.obj);
                break;
            case IDiyMessage.RECOMMAND_PRODUCT_ACTION:
                handleRecommandProductResult((List<RecommandProductBean>) msg.obj);
                break;
        }
    }

    private void handleRecommandProductResult(List<RecommandProductBean> datas) {
        mRecommandProductAdapter.setDatas(datas);
        mRecommandProductAdapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mRecommendGv, 2);
    }

    private void handleSeckillResult(List<SecondKillBean> datas) {
        mSecondKillAdapter.setDatas(datas);
        mSecondKillAdapter.notifyDataSetChanged();
    }

    private void handleBannerResult(List<BannerBean> datas) {
        mBannerAdapter.setDatas(datas);
        mBannerAdapter.notifyDataSetChanged();
        mAdRl.setVisibility(datas.size() > 0 ? View.VISIBLE : View.GONE);

        BannerIndicatorUtil.initItemViews(datas.size(), getActivity(), mAdIndicator);
        BannerIndicatorUtil.changeBannerIndicator(mAdIndicator, 0);
        if (datas.size() <= 0) {
            return;
        }
        startScrollBanner(datas);
    }

    private void startScrollBanner(final List<BannerBean> datas) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        changeCurrentBannerIndex(datas);
                    }
                });
            }
        }, 3 * 1000, 3 * 1000);
    }

    private void changeCurrentBannerIndex(List<BannerBean> datas) {
        int lastIndex = mAdVp.getCurrentItem();
        lastIndex++;
        if (lastIndex > datas.size() - 1) {
            lastIndex = 0;
        }
        mAdVp.setCurrentItem(lastIndex, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        StopScrollBanner();
    }

    private void StopScrollBanner() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        mController.sendAsyncMessage(IDiyMessage.BANNER_ACTION, 1);
        mController.sendAsyncMessage(IDiyMessage.SECOND_KILL_ACTION);
        mController.sendAsyncMessage(IDiyMessage.RECOMMAND_PRODUCT_ACTION);
    }

    @Override
    protected void initController() {
        mController = new HomeController(getActivity());
        mController.setListener(this);
    }

    private void initView(View view) {
        mAdRl = (RelativeLayout) view.findViewById(R.id.ad_rl);
        mAdVp = (ViewPager) view.findViewById(R.id.ad_vp);
        mAdIndicator = (LinearLayout) view.findViewById(R.id.ad_indicator);
        mSecondKillView = (RecyclerView) view.findViewById(R.id.second_kill_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSecondKillView.setLayoutManager(linearLayoutManager);
        mSecondKillAdapter = new SecondKillAdapter(getActivity());
        mSecondKillView.setAdapter(mSecondKillAdapter);
        mSecondKillView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        mRecommendGv = (GridView) view.findViewById(R.id.recommend_gv);
        mRecommandProductAdapter = new RecommandProductAdapter(getContext());
        mRecommendGv.setAdapter(mRecommandProductAdapter);
        mBannerAdapter = new BannerAdapter(getActivity());
        mAdVp.setAdapter(mBannerAdapter);
        mAdVp.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                BannerIndicatorUtil.changeBannerIndicator(mAdIndicator, position);
            }
        });
    }
}

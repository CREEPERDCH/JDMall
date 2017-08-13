package com.creeperdch.jdmall.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.adapter.OrderListContainerAdapter;
import com.creeperdch.jdmall.listener.OnPageChangeListenerAdapter;

import java.util.ArrayList;

public class OrderListActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<View> mIndicators = new ArrayList<>();
    private ArrayList<LinearLayout> mIndicatorsContainer = new ArrayList<>();
    private ViewPager mContainerVp;
    private OrderListContainerAdapter mContainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initView();
    }

    @Override
    protected void initView() {
        //看到的下划线
        mIndicators.add(findViewById(R.id.all_order_view));
        mIndicators.add(findViewById(R.id.wait_pay_view));
        mIndicators.add(findViewById(R.id.wait_receive_view));
        mIndicators.add(findViewById(R.id.wait_sure_view));
        //整体的Item指示器容器
        mIndicatorsContainer.add((LinearLayout) findViewById(R.id.all_order_ll));
        mIndicatorsContainer.add((LinearLayout) findViewById(R.id.wait_pay_ll));
        mIndicatorsContainer.add((LinearLayout) findViewById(R.id.wait_receive_ll));
        mIndicatorsContainer.add((LinearLayout) findViewById(R.id.wait_sure_ll));

        for (LinearLayout itemContainer : mIndicatorsContainer) {
            itemContainer.setOnClickListener(this);
        }

        mContainerVp = findViewById(R.id.vp);
        mContainerAdapter = new OrderListContainerAdapter(getSupportFragmentManager());
        mContainerVp.setAdapter(mContainerAdapter);
        mContainerVp.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                changeIndicator(mIndicators.get(position));
            }
        });
    }

    private void changeIndicator(View view) {
        for (View indicator : mIndicators) {
            indicator.setVisibility(View.INVISIBLE);
        }
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        mContainerVp.setCurrentItem(mIndicatorsContainer.indexOf(v), true);
    }
}

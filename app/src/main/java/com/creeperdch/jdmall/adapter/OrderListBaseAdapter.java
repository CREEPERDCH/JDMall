package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.bean.OrderListBean;
import com.creeperdch.jdmall.utils.ImageUtil;

import java.util.List;

public abstract class OrderListBaseAdapter extends JDBaseAdapter<OrderListBean> {
    public OrderListBaseAdapter(Context context) {
        super(context);
    }

    protected void showOrderProductIv(LinearLayout containerLl, String dataJson) {
        //先隐藏订单内部所有的商品控件
        int containerCount = containerLl.getChildCount();
        for (int index = 0; index < containerCount; index++) {
            containerLl.getChildAt(index).setVisibility(View.INVISIBLE);
        }
        List<String> datas = JSON.parseArray(dataJson, String.class);
        int dataCount = datas.size();
        int realCount = Math.min(containerCount, dataCount);
        for (int index = 0; index < realCount; index++) {
            ImageView iv = (ImageView) containerLl.getChildAt(index);
            ImageUtil.loadImage(mContext, datas.get(index), iv);
            iv.setVisibility(View.VISIBLE);
        }
    }
}

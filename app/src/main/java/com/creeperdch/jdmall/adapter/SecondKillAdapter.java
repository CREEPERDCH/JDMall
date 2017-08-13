package com.creeperdch.jdmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.SecondKillBean;
import com.creeperdch.jdmall.consf.HttpConstant;

import java.util.List;

/*
 * Created by wwwwy on 2017/8/6.
 */

public class SecondKillAdapter extends RecyclerView.Adapter<SecondKillAdapter.SecKillViewHolder> {

    private Context mContext;
    private List<SecondKillBean> mDatas;

    public void setDatas(List<SecondKillBean> datas) {
        this.mDatas = datas;
    }

    public SecondKillAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public SecKillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SecKillViewHolder holder = new SecKillViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_seckull_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SecKillViewHolder holder, int position) {
        Glide.with(mContext).load(HttpConstant.BASE_URL + mDatas.get(position).getIconUrl()).into(holder.iconIv);
        holder.nowPriceTv.setText("$ " + mDatas.get(position).getPointPrice());
        holder.originalPriceTv.setText("$ " + mDatas.get(position).getAllPrice() + "");
        holder.originalPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }


    static class SecKillViewHolder extends RecyclerView.ViewHolder {

        ImageView iconIv;
        TextView nowPriceTv;
        TextView originalPriceTv;

        public SecKillViewHolder(View view) {
            super(view);
            iconIv = (ImageView) view.findViewById(R.id.image_iv);
            nowPriceTv = (TextView) view.findViewById(R.id.nowprice_tv);
            originalPriceTv = (TextView) view.findViewById(R.id.normalprice_tv);
        }
    }
}

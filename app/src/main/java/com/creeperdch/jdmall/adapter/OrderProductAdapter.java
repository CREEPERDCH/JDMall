package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/15.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.OrderProductBean;
import com.creeperdch.jdmall.utils.ImageUtil;

public class OrderProductAdapter extends JDBaseAdapter<OrderProductBean> {
    public OrderProductAdapter(Context context) {
        super(context);
    }

    class ViewHolder {
        ImageView pIconIv;
        TextView pNameTv;
        TextView buyCountTv;
        TextView priceTv;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.order_details_products_item, parent, false);
            holder = new ViewHolder();
            holder.pIconIv = convertView.findViewById(R.id.p_icon_iv);
            holder.pNameTv = convertView.findViewById(R.id.p_name_iv);
            holder.buyCountTv = convertView.findViewById(R.id.p_buycount_iv);
            holder.priceTv = convertView.findViewById(R.id.p_price_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderProductBean bean = mDatas.get(position);
        ImageUtil.loadImage(mContext, bean.getPiconUrl(), holder.pIconIv);
        holder.pNameTv.setText(bean.getPname());
        holder.buyCountTv.setText("x " + bean.getBuyCount());
        holder.priceTv.setText("$ " + bean.getAmount());
        return convertView;
    }
}

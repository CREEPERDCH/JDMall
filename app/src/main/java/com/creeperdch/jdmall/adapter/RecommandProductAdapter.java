package com.creeperdch.jdmall.adapter;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.RecommandProductBean;
import com.creeperdch.jdmall.utils.ImageUtil;

public final class RecommandProductAdapter extends JDBaseAdapter<RecommandProductBean> {
    public RecommandProductAdapter(Context mContext) {
        super(mContext);
    }

    class ViewHolder {
        ImageView productIv;
        TextView productNameTv;
        TextView priceTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recommend_gv_item, parent, false);
            holder = new ViewHolder();
            holder.productIv = (ImageView) convertView.findViewById(R.id.product_iv);
            holder.productNameTv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.priceTv = (TextView) convertView.findViewById(R.id.price_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecommandProductBean bean = mDatas.get(position);
        ImageUtil.loadImage(mContext, bean.getIconUrl(), holder.productIv);
        holder.productNameTv.setText(bean.getName());
        holder.priceTv.setText("¥ " + bean.getPrice());
        return convertView;
    }
}

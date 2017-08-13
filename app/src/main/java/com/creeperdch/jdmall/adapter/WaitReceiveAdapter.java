package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.OrderListBean;
import com.creeperdch.jdmall.consf.OrderStatus;

public class WaitReceiveAdapter extends OrderListBaseAdapter {
    public WaitReceiveAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.waitrecive_order_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderListBean bean = mDatas.get(position);
        holder.orderNoTv.setText("订单编号:" + bean.getOrderNum());
        holder.orderStateTv.setText(OrderStatus.getOrderStatus(bean.getStatus()));
        showOrderProductIv(holder.pContainerLl, bean.getItems());
        holder.priceTv.setText("$ " + bean.getTotalPrice());
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView orderNoTv;
        public TextView orderStateTv;
        public View divider;
        public LinearLayout pContainerLl;
        public View pDivider;
        public TextView priceTv;
        public Button doBtn;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.orderNoTv = rootView.findViewById(R.id.order_no_tv);
            this.orderStateTv = rootView.findViewById(R.id.order_state_tv);
            this.divider = rootView.findViewById(R.id.divider);
            this.pContainerLl = rootView.findViewById(R.id.p_container_ll);
            this.pDivider = rootView.findViewById(R.id.p_divider);
            this.priceTv = rootView.findViewById(R.id.price_tv);
            this.doBtn = rootView.findViewById(R.id.do_btn);
        }
    }
}

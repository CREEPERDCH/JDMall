package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/13.
 */

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.activity.AlipayActivity;
import com.creeperdch.jdmall.bean.OrderListBean;
import com.creeperdch.jdmall.consf.OrderStatus;
import com.creeperdch.jdmall.listener.IOrderCancelListener;

public class WaitPayAdapter extends OrderListBaseAdapter {

    private IOrderCancelListener mListener;

    public void setListener(IOrderCancelListener listener) {
        this.mListener = listener;
    }

    public WaitPayAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.waitpay_order_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final OrderListBean bean = mDatas.get(position);
        holder.orderNoTv.setText("订单编号:" + bean.getOrderNum());
        holder.orderStateTv.setText(OrderStatus.getOrderStatus(bean.getStatus()));
        showOrderProductIv(holder.pContainerLl, bean.getItems());
        holder.priceTv.setText("$ " + bean.getTotalPrice());
        holder.doBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlipayActivity.class);
                intent.putExtra(AlipayActivity.TN_KEY, bean.getTn());
                mContext.startActivity(intent);
            }
        });
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOrderCanceled(bean.getOid());
                }
            }
        });
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
        public Button cancelBtn;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.orderNoTv = rootView.findViewById(R.id.order_no_tv);
            this.orderStateTv = rootView.findViewById(R.id.order_state_tv);
            this.divider = rootView.findViewById(R.id.divider);
            this.pContainerLl = rootView.findViewById(R.id.p_container_ll);
            this.pDivider = rootView.findViewById(R.id.p_divider);
            this.priceTv = rootView.findViewById(R.id.price_tv);
            this.doBtn = rootView.findViewById(R.id.do_btn);
            this.cancelBtn = rootView.findViewById(R.id.cancel_btn);
        }
    }
}

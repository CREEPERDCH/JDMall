package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/10.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.ReceiverAddress;
import com.creeperdch.jdmall.listener.IAddressDelListener;

public class AddressListAdapter extends JDBaseAdapter<ReceiverAddress> {

    private IAddressDelListener mListener;

    public void setListener(IAddressDelListener listener) {
        this.mListener = listener;
    }

    public AddressListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.address_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ReceiverAddress bean = mDatas.get(position);
        holder.nameTv.setText(bean.getReceiverName());
        holder.phoneTv.setText(bean.getReceiverPhone());
        holder.addressTv.setText(bean.getReceiverAddress());
        holder.isDeafultIv.setVisibility(bean.isDefault() ? View.VISIBLE : View.GONE);
        holder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAddressDeleted(bean.getId());
                }
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView isDeafultIv;
        public TextView nameTv;
        public TextView phoneTv;
        public TextView addressTv;
        public TextView deleteTv;
        public RelativeLayout hasReceiverRl;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.isDeafultIv = (ImageView) rootView.findViewById(R.id.isDeafult_iv);
            this.nameTv = (TextView) rootView.findViewById(R.id.name_tv);
            this.phoneTv = (TextView) rootView.findViewById(R.id.phone_tv);
            this.addressTv = (TextView) rootView.findViewById(R.id.address_tv);
            this.deleteTv = (TextView) rootView.findViewById(R.id.delete_tv);
            this.hasReceiverRl = (RelativeLayout) rootView.findViewById(R.id.has_receiver_rl);
        }
    }
}

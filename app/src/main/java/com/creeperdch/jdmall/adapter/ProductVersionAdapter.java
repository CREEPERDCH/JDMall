package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creeperdch.jdmall.R;

public class ProductVersionAdapter extends JDBaseAdapter<String> {

    private int mCurrenTabIndex = -1;

    public ProductVersionAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.brand_item_layout, parent, false);
            holder = (TextView) convertView.findViewById(R.id.brand_tv);
            convertView.setTag(holder);
        } else {
            holder = (TextView) convertView.getTag();
        }
        String bean = mDatas.get(position);
        holder.setText(bean);
        holder.setSelected(position == mCurrenTabIndex);
        return convertView;
    }

    public void tabItem(int tabIndex) {
        mCurrenTabIndex = tabIndex;
        notifyDataSetChanged();
    }
}

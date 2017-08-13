package com.creeperdch.jdmall.adapter;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.Brand;

public class BrandAdapter extends JDBaseAdapter<Brand> {

    private int mCurrentTabIndex = -1;

    public BrandAdapter(Context context) {
        super(context);
    }

    public long getCheckBrandId() {
        return mCurrentTabIndex == -1 ? 0 : mDatas.get(mCurrentTabIndex).getId();
    }

    public void tabItem(int tabIndex) {
        mCurrentTabIndex = tabIndex;
        notifyDataSetChanged();
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
        Brand bean = mDatas.get(position);
        holder.setText(bean.getName());
        holder.setSelected(position == mCurrentTabIndex);
        return convertView;
    }
}

package com.creeperdch.jdmall.adapter;
/*
 * Created by wwwwy on 2017/8/7.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.TopCategoryBean;

public class TopCategoryAdapter extends JDBaseAdapter<TopCategoryBean> {

    private int mCurrentTabIndex = -1;

    public TopCategoryAdapter(Context context) {
        super(context);
    }

    public void tabItem(int tabIndex) {
        mCurrentTabIndex = tabIndex;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView nameTv;
        View rightDividerView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.top_category_item, parent, false);
            holder = new ViewHolder();
            holder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.rightDividerView = convertView.findViewById(R.id.right_divider);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TopCategoryBean topCategoryBean = mDatas.get(position);
        holder.nameTv.setText(topCategoryBean.getName());
        if (position == mCurrentTabIndex) {
            holder.nameTv.setTextColor(0xFF9D000B);
            holder.rightDividerView.setVisibility(View.INVISIBLE);
            holder.nameTv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
        } else {
            holder.nameTv.setTextColor(Color.BLACK);
            holder.rightDividerView.setVisibility(View.VISIBLE);
            holder.nameTv.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}

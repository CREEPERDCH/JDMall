package com.creeperdch.jdmall.adapter;
/*
 * Created by CREEPER_D on 2017/8/9.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.bean.GoodCommentBean;
import com.creeperdch.jdmall.ui.RatingBar;
import com.creeperdch.jdmall.utils.ImageUtil;

import java.util.List;

public class GoodCommentsAdapter extends JDBaseAdapter<GoodCommentBean> {

    private TextView mNameTv;
    private TextView mContentTv;
    private LinearLayout mIamgesContainer;

    public GoodCommentsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fav_comment_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GoodCommentBean bean = mDatas.get(position);
        holder.mRatingBar.setRating(bean.getRate());
        holder.mNameTv.setText(bean.getUserName());
        holder.mContentTv.setText(bean.getComment());
        initImageContainerLl(holder.mIamgesContainer, bean.getImgUrls());
        return convertView;
    }

    /**
     * 展示用户评论的图片
     */
    private void initImageContainerLl(LinearLayout container, String imgUrlJson) {
        List<String> imgUrls = JSON.parseArray(imgUrlJson, String.class);
        container.setVisibility(imgUrls.size() > 0 ? View.VISIBLE : View.GONE);
        int childCount = container.getChildCount();
        for (int index = 0; index < childCount; index++) {
            container.getChildAt(index).setVisibility(View.INVISIBLE);
        }
        for (int index = 0; index < imgUrls.size(); index++) {
            ImageView iv = (ImageView) container.getChildAt(index);
            ImageUtil.loadImage(mContext, imgUrls.get(index), iv);
            iv.setVisibility(View.VISIBLE);
        }
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mNameTv;
        public TextView mContentTv;
        public LinearLayout mIamgesContainer;
        public RatingBar mRatingBar;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mNameTv = (TextView) rootView.findViewById(R.id.name_tv);
            this.mContentTv = (TextView) rootView.findViewById(R.id.content_tv);
            this.mIamgesContainer = (LinearLayout) rootView.findViewById(R.id.iamges_container);
            this.mRatingBar = (RatingBar) rootView.findViewById(R.id.rating_bar);
        }

    }
}

package com.creeperdch.jdmall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.creeperdch.jdmall.R;

/*
 * Created by CREEPER_D on 2017/8/9.
 */

public class RatingBar extends LinearLayout {
    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * max为 5
     */
    public void setRating(int count) {//3
        int childCount = getChildCount();//5
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) getChildAt(i);
            if (count > i) {
                iv.setImageResource(R.drawable.start_selected);
            } else {
                iv.setImageResource(R.drawable.start_normal);
            }
        }
    }
}

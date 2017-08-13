package com.creeperdch.jdmall.utils;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.creeperdch.jdmall.R;

public class BannerIndicatorUtil {

    public static void initItemViews(int totalSize, Context context, LinearLayout mAdIndicator) {
        for (int i = 0; i < totalSize; i++) {
            ImageView iv = getImageView(context);
            mAdIndicator.addView(iv);
        }
    }

    @NonNull
    private static ImageView getImageView(Context context) {
        ImageView iv = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        params.setMargins(15, 0, 0, 0);
        iv.setLayoutParams(params);
        iv.setBackgroundResource(R.drawable.ad_indicator_bg);
        return iv;
    }

    public static void changeBannerIndicator(LinearLayout mAdIndicator, int newIndex) {
        int childCount = mAdIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            mAdIndicator.getChildAt(i).setSelected(i == newIndex);
        }
    }
}

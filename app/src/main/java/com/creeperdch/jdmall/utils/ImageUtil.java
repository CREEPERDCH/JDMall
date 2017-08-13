package com.creeperdch.jdmall.utils;
/*
 * Created by wwwwy on 2017/8/6.
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.creeperdch.jdmall.consf.HttpConstant;

public class ImageUtil {

    public static void loadImage(Context context, String subImageUrl, ImageView imageView) {
        Glide.with(context).load(HttpConstant.BASE_URL + subImageUrl).into(imageView);
    }
}

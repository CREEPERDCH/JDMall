package com.creeperdch.jdmall.utils;
/*
 * Created by CREEPERDCH on 2017/8/4.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class ActivityUtil {
    public static void startNew(Context context, Class<? extends AppCompatActivity> clazz, boolean isFinish) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }
}
